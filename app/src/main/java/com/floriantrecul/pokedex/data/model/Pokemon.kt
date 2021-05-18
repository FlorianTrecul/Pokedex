package com.floriantrecul.pokedex.data.model

data class Pokemon(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val moves: List<PokemonMove>,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>
)
