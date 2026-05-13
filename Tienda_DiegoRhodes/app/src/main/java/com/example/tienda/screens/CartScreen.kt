package com.example.tienda.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tienda.data.dto.CartItemDto
import com.example.tienda.viewmodel.CartViewModel
import androidx.compose.material3.*


@Composable
fun CartScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel = viewModel()
) {
    var productToDelete by remember { mutableStateOf<CartItemDto?>(null) }

    LaunchedEffect(Unit) {
        cartViewModel.loadCart()
    }

    val cart = cartViewModel.cartResponse

    if (productToDelete != null) {
        AlertDialog(
            onDismissRequest = { productToDelete = null },
            title = { Text(text = "Eliminar del carrito") },
            text = { Text(text = "¿Quires eliminar '${productToDelete?.name}' del carrito?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        productToDelete?.let { cartViewModel.removeItem(it.prodId) }
                        productToDelete = null
                    }
                ) {
                    Text("ELIMINAR", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { productToDelete = null }) {
                    Text("CANCELAR")
                }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Mi Carrito")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (cart == null || cart.items.isEmpty()) {
            Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                if (cartViewModel.isLoading){}
                else Text("El carrito está vacío")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(cart.items) { item ->

                    val isSelected = productToDelete?.prodId == item.prodId

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { productToDelete = item },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceVariant
                        ),
                        elevation = CardDefaults.cardElevation(if (isSelected) 8.dp else 2.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = item.name)
                                Text(text = "Cantidad: ${item.uni}")
                            }
                        }
                    }
                }
            }
        }
    }
}