package com.floriantrecul.pokedex.ui.components

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun PokemonIcon(icon: ImageVector) {
    Icon(icon, contentDescription = "Description")
}