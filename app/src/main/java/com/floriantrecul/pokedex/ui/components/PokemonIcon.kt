package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage

@Composable
fun PokemonIcon(icon: Int, height: Dp = 38.dp, width: Dp = 38.dp, name: String) {
    CoilImage(
        data = icon,
        contentDescription = name,
        modifier = Modifier
            .height(height)
            .width(width),
        contentScale = ContentScale.Inside,
        fadeIn = true
    ) {
        CircularProgressLoader()
    }
}