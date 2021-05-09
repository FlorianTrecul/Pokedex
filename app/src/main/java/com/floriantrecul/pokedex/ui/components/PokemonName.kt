package com.floriantrecul.pokedex.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun PokemonName(name: String?, modifier: Modifier = Modifier) {
    Text(
        text = name?.capitalize(Locale.getDefault()) ?: "",
        modifier = modifier,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.White,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}