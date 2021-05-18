package com.floriantrecul.pokedex.ui.data.model

import com.floriantrecul.pokedex.data.model.PokemonAbility
import com.floriantrecul.pokedex.data.model.PokemonMove
import com.floriantrecul.pokedex.data.model.PokemonStat
import com.floriantrecul.pokedex.data.model.PokemonType

data class PokemonDetailsUiModel(
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