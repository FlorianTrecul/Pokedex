package com.floriantrecul.pokedex.ui.data.model

import com.floriantrecul.pokedex.data.model.PokemonType

data class PokemonDetailsUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val pokemonAbout: PokemonAboutUiModel,
    val moves: List<PokemonMovesUiModel>,
    val stats: List<PokemonBaseStatsUiModel>,
    val types: List<PokemonType>
)