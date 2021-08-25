package com.floriantrecul.pokedex.ui.screens.details.tabs

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.ui.data.model.PokemonAboutUiModel
import com.floriantrecul.pokedex.ui.data.model.PokemonBaseStatsUiModel
import com.floriantrecul.pokedex.ui.data.model.PokemonMovesUiModel

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(
    @StringRes var title: Int,
    var screen: ComposableFun
) {
    @ExperimentalFoundationApi
    data class About(val pokemonAbout: PokemonAboutUiModel) : TabItem(R.string.pokemon_detail_about_tab, { AboutTab(pokemonAbout) })
    data class BaseStats(val stats: List<PokemonBaseStatsUiModel>) : TabItem(R.string.pokemon_detail_base_stats_tab, { BaseStatsTab(stats) })
    data class Moves(val moves: List<PokemonMovesUiModel>) : TabItem(R.string.pokemon_detail_moves_tab, { MovesTab(moves) })
}