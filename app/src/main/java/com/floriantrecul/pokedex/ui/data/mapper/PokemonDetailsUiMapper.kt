package com.floriantrecul.pokedex.ui.data.mapper

import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel

class PokemonDetailsUiMapper(private val pokemonBaseStatsUiMapper: PokemonBaseStatsUiMapper) {

    fun mapToDomainModelPokemonDetailUi(pokemon: Pokemon): PokemonDetailsUiModel = PokemonDetailsUiModel(
        id = pokemon.id,
        name = pokemon.name,
        description = pokemon.description,
        imageUrl = pokemon.imageUrl,
        height = pokemon.height,
        weight = pokemon.weight,
        abilities = pokemon.abilities,
        moves = pokemon.moves,
        stats = pokemon.stats.map { pokemonBaseStatsUiMapper.mapToDomainModelPokemonBaseStatsUi(it) },
        types = pokemon.types
    )
}