package com.cs4520.assignment5.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment4.Data.Entities.Product
import com.cs4520.assignment4.Data.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository = ProductRepository(application.applicationContext)

    private val _uiState = MutableLiveData(ProductListUiState(isLoading = true))
    val uiState: LiveData<ProductListUiState> = _uiState

    fun refreshProducts() {
        viewModelScope.launch {
            try {
                val results = repository.getAllProducts()

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