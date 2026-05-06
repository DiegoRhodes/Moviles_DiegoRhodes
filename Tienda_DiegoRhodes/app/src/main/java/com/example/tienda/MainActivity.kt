package com.example.tienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tienda.screens.LoginScreen
import com.example.tienda.ui.theme.TiendaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TiendaTheme() {
                LoginScreen(
                    viewModel = viewModel(),
                    onLoginSuccess = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TiendaTheme() {
        LoginScreen(
            viewModel = viewModel(),
            onLoginSuccess = {}
        )
    }
}