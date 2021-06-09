package com.floriantrecul.pokedex.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import java.util.*

@Composable
fun PokemonName(
    name: String?,
    style: TextStyle = MaterialTheme.typography.h4,
    modifier: Modifier = Modifier
) {
    Text(
        text = name?.capitalize(Locale.getDefault()) ?: "",
        modifier = modifier,
        style = style,
        color = Color.White,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}