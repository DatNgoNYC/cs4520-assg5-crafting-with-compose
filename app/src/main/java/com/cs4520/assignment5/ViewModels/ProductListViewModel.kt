package com.cs4520.assignment5.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment4.Data.ProductRepository
import com.cs4520.assignment5.Work.FetchProductsWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ProductListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository = ProductRepository(application.applicationContext)

    private val _uiState = MutableLiveData(ProductListUiState(isLoading = true))
    val uiState: LiveData<ProductListUiState> = _uiState

    val workerManager = WorkManager.getInstance(application.applicationContext)


    fun refreshProducts() {
        Log.d("ProductList", "refreshProducts() is being called.")

        _uiState.value = _uiState.value?.copy(
            isLoading = true
        )

        val request = PeriodicWorkRequestBuilder<FetchProductsWorker>(1, TimeUnit.HOURS)
            .setInitialDelay(1, TimeUnit.HOURS)
            .build()

        workerManager.enqueueUniquePeriodicWork(
            "Fetch Products in the next 10 secs.",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            request
        )

        viewModelScope.launch {
            try {
                val results = repository.addRandomProductListToCurrentAndReturnAllProducts()

                Log.d("productList", "$results")

                if (results.isEmpty()) {
                    _uiState.value = _uiState.value?.copy(
                        isLoading = false,
                        error = "No products found."
                    )
                } else {
                    _uiState.value = _uiState.value?.copy(
                        isLoading = false,
                        products = results
                    )
                }
            } catch (e: Error) {
                _uiState.value = _uiState.value?.copy(
                    isLoading = false,
                    error = "Error fetching products, uh oh. MSG: ${e.message}"
                )
            }
        }
    }
}

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)