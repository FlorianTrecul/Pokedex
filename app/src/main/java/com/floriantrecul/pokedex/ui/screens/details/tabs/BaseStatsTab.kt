package com.floriantrecul.pokedex.ui.screens.details.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.floriantrecul.pokedex.ui.components.PokemonBaseStatsRow
import com.floriantrecul.pokedex.ui.data.model.PokemonBaseStatsUiModel

@Composable
fun BaseStatsTab(stats: List<PokemonBaseStatsUiModel>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(stats) { stat ->
                PokemonBaseStatsRow(pokemonBaseStatsUiModel = stat)
            }
        }

    }
}