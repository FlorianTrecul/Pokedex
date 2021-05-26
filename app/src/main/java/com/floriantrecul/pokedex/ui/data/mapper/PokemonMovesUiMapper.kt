package com.floriantrecul.pokedex.ui.data.mapper

import com.floriantrecul.pokedex.data.model.PokemonMove
import com.floriantrecul.pokedex.ui.data.model.PokemonMovesUiModel

class PokemonMovesUiMapper {

    fun mapToDomainModelPokemonMovesUi(pokemonMove: PokemonMove): PokemonMovesUiModel = PokemonMovesUiModel(
        name = pokemonMove.name
    )
}