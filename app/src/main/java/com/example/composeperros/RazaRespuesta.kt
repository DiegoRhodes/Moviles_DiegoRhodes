package com.example.composeperros

data class RazaRespuesta(
    val message: Map<String, List<String>>,
    val status: String
)