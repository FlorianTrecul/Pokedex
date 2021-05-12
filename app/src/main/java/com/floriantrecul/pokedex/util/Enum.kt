package com.floriantrecul.pokedex.util

import androidx.annotation.StringRes
import com.floriantrecul.pokedex.R

enum class PokemonDetailsTabs(
    @StringRes val titleRes: Int,
) {
    About(R.string.pokemon_detail_about_tab),
    BaseStats(R.string.pokemon_detail_base_stats_tab),
    Moves(R.string.pokemon_detail_moves_tab),
}