package com.example.composeperros

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun navegacion(){
    var navController = rememberNavController()

    NavHost(navController, startDestination = "lista"){
        composable("lista"){
            listaPerros(navController)
        }
        composable("detalle/{raza}"){
            var raza = it.arguments?.getString("raza") ?:""
            detallePerro(raza,null,navController)
        }
        composable("detalle/{raza}/{subraza}") {
            val raza = it.arguments?.getString("raza") ?: ""
            val subraza = it.arguments?.getString("subraza")
            detallePerro(raza, subraza, navController)
        }
    }
}