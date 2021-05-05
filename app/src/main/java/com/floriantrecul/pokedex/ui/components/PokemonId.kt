package com.floriantrecul.pokedex.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun PokemonId(id: Int?, modifier: Modifier = Modifier) {
    Text(
        text = "#%03d".format(id),
        modifier = modifier,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            color = Color.White.copy(0.7f),
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}