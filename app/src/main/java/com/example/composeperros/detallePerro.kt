package com.example.composeperros

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun detallePerro(
    raza: String,
    subraza: String? = null,
    navController: NavController
) {

    var myViewModel: ListaPerrosViewModel = viewModel()
    var perro = myViewModel.detallePerro.collectAsState().value

    LaunchedEffect(raza, subraza) {
        myViewModel.cargarDetallePerro(raza, subraza)
    }

    Column {

        LazyColumn {
            items(perro?.message ?: emptyList()) { foto ->

                AsyncImage(
                    model = foto,
                    contentDescription = "foto de perro",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Volver")
        }
    }
}