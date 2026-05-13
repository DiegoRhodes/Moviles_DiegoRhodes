package com.example.tienda.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tienda.data.repository.TiendaRepository
import com.example.tienda.domain.SessionManager
import com.example.tienda.states.LoginState
import kotlinx.coroutines.launch
import retrofit2.HttpException


class LoginViewModel : ViewModel() {

    private val repo = TiendaRepository()

    var state by mutableStateOf(LoginState())
        private set

    fun login(username: String, password: String) {
        viewModelScope.launch {
            state = state.copy(error = null)

            try {
                val response = repo.login(username, password)
                SessionManager.token = response.accessToken
                SessionManager.username = username
                state = state.copy(success = true)

            } catch (e: HttpException) {
                val mensajePersonalizado = when (e.code()) {
                    401 -> "Usuario o contraseña incorrectos"
                    else -> "Error inesperado: ${e.code()}"
                }
                state = state.copy(error = mensajePersonalizado)
            } catch (e: Exception) {
                state = state.copy(error = "No se pudo conectar con el servidor")
            }
        }
    }
}