package com.example.tienda.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.tienda.viewmodel.CategoriesViewModel

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
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        AsyncImage(
                            model = product.prodImage,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Fit
                        )


                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = product.prodName,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${product.prodPrice} €",
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${product.prodImage} ",
                            )
                        }
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

