package com.floriantrecul.pokedex.ui.data.mapper

import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel

class PokemonDetailsUiMapper {

    fun mapToDomainModelPokemonDetailUi(pokemon: Pokemon): PokemonDetailsUiModel = PokemonDetailsUiModel(
        id = pokemon.id,
        name = pokemon.name,
        description = pokemon.description,
        imageUrl = pokemon.imageUrl,
        height = pokemon.height,
        weight = pokemon.weight,
        abilities = pokemon.abilities,
        moves = pokemon.moves,
        stats = pokemon.stats,
        types = pokemon.types
    )

}