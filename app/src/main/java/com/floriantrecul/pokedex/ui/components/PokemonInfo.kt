package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonInfoTitle(title: Int) {
    Text(
        text = stringResource(title),
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
        ),
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    )
}

@Composable
fun PokemonInfoLabel(label: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(label),
        modifier = modifier.padding(top = 8.dp)
    )
}

@Composable
fun PokemonInfoText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier.padding(top = 8.dp),
        color = Color.Black
    )
}