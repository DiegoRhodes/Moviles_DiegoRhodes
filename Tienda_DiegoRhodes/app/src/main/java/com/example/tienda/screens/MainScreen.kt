package com.example.tienda.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tienda.navigation.Routes

@Composable
fun MainScreen(navController: NavHostController) {

    val innerNavController = rememberNavController()

    Scaffold(
       // topBar = {TopBar(navController) },
        bottomBar = { BottomBar(innerNavController) }
    ) { padding ->

        NavHost(
            navController = innerNavController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(padding)
        ) {

            composable(Routes.HOME) {
                HomeScreen()
            }

            composable(Routes.PRODUCTS) {
                ProductsScreen()
            }

            composable(Routes.CART) {
                CartScreen()
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    NavigationBar{

        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(Routes.HOME)},
            icon = {Icon(Icons.Default.Home, null)},
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(Routes.PRODUCTS)},
            icon = {Icon(Icons.Default.List, null)},
            label = { Text("Productos") }
        )

        NavigationBarItem(
            selected = false,
            onClick = {navController.navigate(Routes.CART)},
            icon = {Icon(Icons.Default.ShoppingCart, null)},
            label = { Text("Carrito") }
        )
    }
}