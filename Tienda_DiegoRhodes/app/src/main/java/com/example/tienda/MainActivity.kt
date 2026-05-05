package com.example.tienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tienda.screens.LoginPantalla
import com.example.tienda.ui.theme.TIENDATheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TIENDATheme {
                LoginPantalla()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TIENDATheme {
        LoginPantalla()
    }
}