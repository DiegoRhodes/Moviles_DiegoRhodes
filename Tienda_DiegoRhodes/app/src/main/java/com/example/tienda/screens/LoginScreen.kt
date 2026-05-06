package com.example.tienda.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tienda.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {

    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state = viewModel.state

    if (state.success) {
        onLoginSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.login(usuario, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }


        state.error?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}