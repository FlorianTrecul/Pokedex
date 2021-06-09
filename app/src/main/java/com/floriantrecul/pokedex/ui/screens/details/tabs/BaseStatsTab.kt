package com.floriantrecul.pokedex.ui.screens.details.tabs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.floriantrecul.pokedex.ui.components.PokemonBaseStatsRow
import com.floriantrecul.pokedex.ui.data.model.PokemonBaseStatsUiModel

@Composable
fun BaseStatsTab(stats: List<PokemonBaseStatsUiModel>) {
    LazyColumn {
        items(stats) { stat ->
            PokemonBaseStatsRow(pokemonBaseStatsUiModel = stat)
        }
    }
}