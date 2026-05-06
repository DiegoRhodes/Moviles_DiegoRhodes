package com.example.tienda.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tienda.domain.SessionManager
import com.example.tienda.navigation.Routes
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource
import com.example.tienda.R

@Composable
fun MainScreen(navController: NavHostController) {

    val innerNavController = rememberNavController()
    val username = SessionManager.username ?: "Usuario"
    Scaffold(
       topBar = {TopBar(navController) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {

    val username = SessionManager.username ?: "Usuario"

    TopAppBar(
        title = {
            Text(text = username)
        },
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.icono),
                contentDescription = "Logo"
            )
        },
        actions = {
            IconButton(
                onClick = {
                    SessionManager.token = null
                    SessionManager.username = null
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout"
                )
            }
        }
    )
}