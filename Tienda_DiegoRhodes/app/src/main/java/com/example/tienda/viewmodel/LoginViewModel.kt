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


class LoginViewModel : ViewModel() {

    private val repo = TiendaRepository()

    var state by mutableStateOf(LoginState())
        private set

    fun login(username: String, password: String) {

        viewModelScope.launch {
            try {
                val response = repo.login(username, password)

                SessionManager.token = response.accessToken
                Log.d("Token", response.accessToken)

                state = state.copy(success = true)

            } catch (e: Exception) {
                state = state.copy(error = e.message ?: "Error desconocido")
            }
        }
    }
}