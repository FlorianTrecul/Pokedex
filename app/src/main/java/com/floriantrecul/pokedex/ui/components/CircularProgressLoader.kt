package com.floriantrecul.pokedex.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale

@Composable
fun CircularProgressLoader() {
    CircularProgressIndicator(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.scale(0.5f)
    )
}