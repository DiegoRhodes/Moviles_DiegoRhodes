package com.example.tienda.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tienda.viewmodel.CategoriesViewModel
import com.example.tienda.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    productsViewModel: ProductsViewModel = viewModel(),
    categoriesViewModel: CategoriesViewModel = viewModel()
) {
    val products = productsViewModel.productsToShow
    val categories = categoriesViewModel.categories
    val currentPage = productsViewModel.currentPage

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
                Button(onClick = { productsViewModel.loadAllProducts() }) {
                    Text("Todos")
                }
            }

            items(categories) { category ->
                Button(
                    onClick = { productsViewModel.loadProductsByCategory(category.categoryId) },
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Text(category.name)
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "${product.prodName}"
                        )
                        Text(text = "${product.prodPrice} €")
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { productsViewModel.previousPage() },
                enabled = currentPage > 0
            ) {
                Text("Atrás")
            }

            Text(
                text = "Página ${currentPage + 1}",
                style = MaterialTheme.typography.bodyMedium
            )

            Button(
                onClick = { productsViewModel.nextPage() },
                enabled = productsViewModel.hasNextPage()
            ) {
                Text("Siguiente")
            }
        }
    }
}