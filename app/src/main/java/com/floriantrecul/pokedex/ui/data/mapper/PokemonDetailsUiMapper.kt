package com.floriantrecul.pokedex.ui.data.mapper

import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonAbout
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel

class PokemonDetailsUiMapper(
    private val pokemonAboutUiMapper: PokemonAboutUiMapper,
    private val pokemonMovesUiMapper: PokemonMovesUiMapper,
    private val pokemonBaseStatsUiMapper: PokemonBaseStatsUiMapper
) {

    fun mapToDomainModelPokemonDetailUi(pokemon: Pokemon): PokemonDetailsUiModel =
        PokemonDetailsUiModel(
            id = pokemon.id,
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            pokemonAbout = pokemonAboutUiMapper.mapToDomainModelPokemonAboutUi(
                PokemonAbout(
                    pokemon.description,
                    pokemon.height,
                    pokemon.weight,
                    pokemon.abilities
                )
            ),
            moves = pokemon.moves.map { pokemonMovesUiMapper.mapToDomainModelPokemonMovesUi(it) },
            stats = pokemon.stats.map { pokemonBaseStatsUiMapper.mapToDomainModelPokemonBaseStatsUi(it) },
            types = pokemon.types
        )
}