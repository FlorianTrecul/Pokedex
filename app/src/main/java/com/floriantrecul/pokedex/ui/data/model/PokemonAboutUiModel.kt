package com.floriantrecul.pokedex.ui.data.model

import com.floriantrecul.pokedex.data.model.PokemonAbility

data class PokemonAboutUiModel(
    val description: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>
)
