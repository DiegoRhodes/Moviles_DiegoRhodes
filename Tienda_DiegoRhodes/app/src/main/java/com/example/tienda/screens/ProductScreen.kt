package com.example.tienda.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tienda.viewmodel.ProductsViewModel
import androidx.compose.foundation.lazy.items


@Composable
fun ProductsScreen(
    viewModel: ProductsViewModel = viewModel()
) {

    val products = viewModel.products
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {

        when {
            isLoading -> {
                CircularProgressIndicator()
            }

            error != null -> {
                Text(text = error)
            }

            else -> {
                LazyColumn {
                    items(products) { product ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(product.prodName)
                                Text("${product.prodPrice} €")
                            }
                        }
                    }
                }
            }
        }
    }
}