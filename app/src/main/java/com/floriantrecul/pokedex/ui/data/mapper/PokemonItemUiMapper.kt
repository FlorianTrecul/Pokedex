package com.floriantrecul.pokedex.ui.data.mapper

import com.floriantrecul.pokedex.data.model.PokemonItem
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel

class PokemonItemUiMapper {

    fun mapToDomainModelPokemonItemUi(pokemonItem: PokemonItem): PokemonItemUiModel = PokemonItemUiModel(
        id = pokemonItem.id,
        name = pokemonItem.name,
        imageUrl = pokemonItem.imageUrl,
        types = pokemonItem.types
    )
}