package com.floriantrecul.pokedex.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color

@Composable
fun CircularProgressLoader(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary
) {
    CircularProgressIndicator(
        color = color,
        modifier = modifier.scale(0.5f)
    )
}