package com.example.tienda.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tienda.viewmodel.ProductsViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import com.example.tienda.viewmodel.CategoriesViewModel


@Composable
fun ProductsScreen(
    productsViewModel: ProductsViewModel = viewModel(),
    categoriesViewModel: CategoriesViewModel = viewModel()
) {

    val products = productsViewModel.products
    val categories = categoriesViewModel.categories

    LaunchedEffect(Unit) {
        productsViewModel.loadAllProducts()
        categoriesViewModel.loadCategories()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {

            item {
                Button(
                    onClick = {
                        productsViewModel.loadAllProducts()
                    }
                ) {
                    Text("Todos")
                }
            }

            items(categories) { category ->
                Button(
                    onClick = {
                        productsViewModel.loadProductsByCategory(category.categoryId)
                    }
                ) {
                    Text(category.name)
                }
            }
        }

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