package com.floriantrecul.pokedex.ui.screens.list

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun PokemonListScreen(pokemonDetailsScreen: (String) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Hello You!")
    }
}