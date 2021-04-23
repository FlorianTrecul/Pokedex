package com.floriantrecul.pokedex.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    )
}