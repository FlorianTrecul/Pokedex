package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PokemonIcon(icon: Int, height: Dp = 38.dp, width: Dp = 38.dp, name: String) {
    CoilImage(
        data = icon,
        contentDescription = name,
        modifier = Modifier
            .height(height)
            .width(width),
        contentScale = ContentScale.Inside
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.scale(0.5f)
        )
    }
}