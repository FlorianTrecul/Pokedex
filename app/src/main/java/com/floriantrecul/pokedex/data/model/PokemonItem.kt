package com.floriantrecul.pokedex.data.model

data class PokemonItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>
)
