package com.cs4520.assignment5.UI.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cs4520.assignment5.ViewModels.ProductListViewModel

@Composable
fun ProductListScreen(productListViewModel: ProductListViewModel = viewModel()) {
    val uiState by productListViewModel.products.collectAs

    // collect the state variables from the view model to listen for changes to compose

    // use LaunchedEffects to load the products

    // ui stuff using the state variables

}

@Composable
fun ProductList() {

}

@Composable
fun ProductItem() {

}

