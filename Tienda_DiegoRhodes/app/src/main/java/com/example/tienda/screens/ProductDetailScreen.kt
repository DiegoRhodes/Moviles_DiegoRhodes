package com.example.tienda.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tienda.viewmodel.CartViewModel
import com.example.tienda.viewmodel.ProductsViewModel

@Composable
fun ProductDetailScreen(
    productId: Long,
    navController: NavHostController,
    productsViewModel: ProductsViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {
    LaunchedEffect(productId) {
        productsViewModel.loadProductById(productId)
        cartViewModel.loadCart()
    }

    val product = productsViewModel.selectedProduct
    var quantity by remember { mutableIntStateOf(1) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Text(text = "Detalle del Producto")
        }

        if (product != null) {
            val itemInCart = cartViewModel.cartResponse?.items?.find { it.prodId == productId }
            val unitsAlreadyInCart = itemInCart?.uni ?: 0
            val availableStock = (product.prodStock - unitsAlreadyInCart).coerceAtLeast(0)

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.prodImage)
                    .setHeader("User-Agent", "Mozilla/5.0")
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(220.dp).padding(16.dp)
            )

            Text(text = product.prodName)
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (availableStock > 0) "Disponibles: $availableStock" else "AGOTADO"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { if (quantity > 1) quantity-- },
                    enabled = availableStock > 0
                ) {
                    Text(text = "-")
                }

                Text(
                    text = "$quantity",
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Button(
                    onClick = { if (quantity < availableStock) quantity++ },
                    enabled = availableStock > 0 && quantity < availableStock
                ) {
                    Text(text = "+")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { cartViewModel.addProduct(productId, quantity) },
                enabled = availableStock > 0 && !cartViewModel.isLoading,
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                if (cartViewModel.isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(if (availableStock > 0) "AÑADIR AL CARRITO" else "SIN STOCK")
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }

    LaunchedEffect(cartViewModel.isAddedSuccess) {
        if (cartViewModel.isAddedSuccess) {
            navController.popBackStack()
            cartViewModel.isAddedSuccess = false
        }
    }
}