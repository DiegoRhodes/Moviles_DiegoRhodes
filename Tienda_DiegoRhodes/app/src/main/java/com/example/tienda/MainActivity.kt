package com.example.tienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tienda.navigation.Routes
import com.example.tienda.screens.LoginScreen
import com.example.tienda.screens.MainScreen
import com.example.tienda.ui.theme.TiendaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = Routes.LOGIN) {

                composable(Routes.LOGIN) {
                    LoginScreen(
                        viewModel = viewModel(),
                        onLoginSuccess = {
                            navController.navigate(Routes.HOME)
                        }
                    )
                }

                composable(Routes.HOME) {
                    MainScreen(navController)
                }
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