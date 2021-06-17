package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun PokemonImage(uri: String, modifier: Modifier = Modifier, contentDescription: String? = null) {
    val painter = rememberCoilPainter(
        request = uri,
        fadeIn = true
    )

    Image(
        painter = painter,
        modifier = modifier,
        contentScale = ContentScale.Inside,
        contentDescription = contentDescription
    )
    when (painter.loadState) {
        is ImageLoadState.Loading -> CircularProgressLoader(modifier = Modifier.size(96.dp))
        is ImageLoadState.Error -> { }
        else -> Unit
    }
}