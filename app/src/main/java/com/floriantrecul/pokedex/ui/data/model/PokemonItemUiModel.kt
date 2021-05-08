package com.floriantrecul.pokedex.ui.data.model

import com.floriantrecul.pokedex.data.model.PokemonType

data class PokemonItemUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>
)
