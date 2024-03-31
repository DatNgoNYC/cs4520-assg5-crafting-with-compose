package com.cs4520.assignment5.ViewModels

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    fun checkCreditentials(username: String, password: String) : Boolean {
        return username === "admin" && password === "admin"
    }
}