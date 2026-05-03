package com.example.composeperros

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Collections.emptyList

class ListaPerrosState {

    val retrofitApi = RetrofitCliente()

    suspend fun listaRazasPerros(): List<String> = withContext(Dispatchers.IO) {

        val respuesta = retrofitApi.retrofitService.getRazas()

        if (respuesta.isSuccessful) {
            respuesta.body()?.message?.keys?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun recuperarFotos(
        raza: String,
        subraza: String? = null
    ): DogRespuesta = withContext(Dispatchers.IO) {

        val respuesta = if (subraza.isNullOrEmpty()) {
            retrofitApi.retrofitService.getImagenes(raza)
        } else {
            retrofitApi.retrofitService.getImagenesSubraza(raza, subraza)
        }

        if (respuesta.isSuccessful) {
            respuesta.body() ?: DogRespuesta(emptyList(), "error")
        } else {
            DogRespuesta(emptyList(), "error")
        }
    }
}