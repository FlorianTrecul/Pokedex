package com.floriantrecul.pokedex.data.model

data class PokemonAbout(
    val description: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>
)
