package com.example.tienda.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tienda.viewmodel.CartViewModel

@Composable
fun CartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = viewModel()
) {
    // Cargamos el carrito al entrar
    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }

    val cart = cartViewModel.cartResponse

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Tu Carrito", style = MaterialTheme.typography.headlineMedium)

        if (cart == null || cart.items.isEmpty()) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text("El carrito está vacío")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cart.items) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                                Text(text = "Cantidad: ${item.uni}")
                            }
                            Text(text = "${item.totalPrice} €", style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }

            Divider()
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total:", style = MaterialTheme.typography.headlineSmall)
                Text(text = "${cart.price} €", style = MaterialTheme.typography.headlineSmall, color = Color.Green)
            }

            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("FINALIZAR COMPRA")
            }
        }
    }
}