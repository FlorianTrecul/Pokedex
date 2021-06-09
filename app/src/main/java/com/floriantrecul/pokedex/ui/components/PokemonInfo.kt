package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun PokemonInfoTitle(title: Int) {
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.onSurface,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    )
}

@Composable
fun PokemonInfoLabel(label: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(label),
        modifier = modifier,
        color = MaterialTheme.colors.onSurface
    )
}

@Composable
fun PokemonInfoText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = MaterialTheme.colors.onSurface,
        modifier = modifier,
        style = MaterialTheme.typography.body1,
    )
}