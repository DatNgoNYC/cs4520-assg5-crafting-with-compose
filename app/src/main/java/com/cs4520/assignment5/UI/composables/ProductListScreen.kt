package com.cs4520.assignment5.UI.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment5.R
import com.cs4520.assignment5.ViewModels.ProductListViewModel

@Composable
fun ProductListScreen() {
    val productListViewModel: ProductListViewModel = viewModel()
    val uiState by productListViewModel.uiState.observeAsState()

    // use LaunchedEffects to load the products
    LaunchedEffect(key1 = true) {
        productListViewModel.refreshProducts()

    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState?.isLoading == true) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn() {
                uiState?.let {
                    items(it.products) { product ->
                        ProductListItem(product)
                    }
                }
            }
        }


    }
}


@Composable
fun ProductListItem(product: Product) {
        Row(
            modifier = Modifier.background(
                colorResource(
                    id = when (product.type) {
                        "Food" -> R.color.light_yellow
                        "Equipment" -> R.color.light_red
                        else -> {
                            R.color.white
                        }
                    }
                )
            ).fillMaxWidth()
        ) {
            Image(painter = painterResource(
                id = when (product.type) {
                    "Food" -> R.drawable.food
                    "Equipment" -> R.drawable.equipment
                    else -> {R.drawable.ic_launcher_foreground}
                }), contentDescription = "",
                modifier = Modifier.size(88.dp))

            Column {
                Text(text = product.name)
                product.expiryDate?.let {
                    Text(text = product.expiryDate)
                }
                Text(text = product.price)
            }
        }
}

@Preview
@Composable
fun ListItemPreview1() {
    ProductListItem(Product("Treadmill", "Equipment", "tomorrow", "$ 32"))
}

@Preview
@Composable
fun ListItemPreview2() {
    ProductListItem(Product("banana", "Food", "yesterday", "$ 32"))
}
