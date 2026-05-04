package com.example.composeperros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaPerrosViewModel : ViewModel() {

    var myState = ListaPerrosState()

    private var _listaRazas = MutableStateFlow<List<String>>(emptyList())
    var listaRazas: StateFlow<List<String>> = _listaRazas

    private var _detallePerro = MutableStateFlow<DogRespuesta?>(null)
    var detallePerro: StateFlow<DogRespuesta?> = _detallePerro

    init {
        cargarRazas()
    }

    fun cargarRazas() {
        viewModelScope.launch {
            _listaRazas.value = myState.listaRazasPerros()
        }
    }

    fun cargarDetallePerro(raza: String, subraza: String? = null) {
        viewModelScope.launch {
            _detallePerro.value = myState.recuperarFotos(raza, subraza)
        }
    }
}