package com.cs4520.assignment5.UI

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.cs4520.assignment5.UI.composables.LoginScreen
import androidx.compose.runtime.getValue
import com.cs4520.assignment5.UI.composables.ProductListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") { LoginScreen { navController.navigate("productList") } }
                composable("productList") { ProductListScreen() }
            }
        }
    }
}