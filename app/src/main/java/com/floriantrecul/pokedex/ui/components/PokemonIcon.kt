package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun PokemonIcon(icon: Int, height: Dp = 38.dp, width: Dp = 38.dp, contentDescription: String) {
    val painter = rememberCoilPainter(
        request = icon,
        fadeIn = true
    )

    Image(
        painter = painter,
        modifier = Modifier
            .height(height)
            .width(width),
        contentScale = ContentScale.Inside,
        contentDescription = contentDescription

    )
    when (painter.loadState) {
        is ImageLoadState.Loading -> CircularProgressLoader()
        is ImageLoadState.Error -> { }
        else -> Unit
    }
}