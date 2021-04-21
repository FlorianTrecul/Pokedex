package com.floriantrecul.pokedex.ui.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun PokedexHomeScreen() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Welcome in the Pokedex trainer !")
    }
}