package com.floriantrecul.pokedex.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PokemonImage(
    uri: String,
    modifier: Modifier,
    contentDescription: String? = null
) {
    CoilImage(
        data = uri,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.scale(0.5f)
        )
    }
}