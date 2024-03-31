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
    private val repository : ProductRepository = ProductRepository(application.applicationContext)
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products
    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadProducts() {

        viewModelScope.launch {
            try {
                val results = repository.getAllProducts()
                if (results.isEmpty()) {
                    _isLoading.postValue(false)
                } else {
                    _isLoading.postValue(false)
                    _products.postValue(results)
                }
            } catch (e: Error) {
                _isLoading.postValue(false)
            }
        }
    }
}

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)