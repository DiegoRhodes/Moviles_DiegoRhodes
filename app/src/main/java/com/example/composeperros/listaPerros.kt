package com.example.composeperros

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun listaPerros(navController: NavController) {

    val myViewModel: ListaPerrosViewModel = viewModel()
    val perros = myViewModel.listaRazas.collectAsState().value

    LazyColumn {

        items(perros) { raza ->

            Text(
                text = raza,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("detalle/$raza")
                    }
                    .padding(16.dp)
            )
        }
    }
}