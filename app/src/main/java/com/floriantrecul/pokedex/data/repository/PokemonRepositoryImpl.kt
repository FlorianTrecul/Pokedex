package com.floriantrecul.pokedex.data.repository

import com.floriantrecul.pokedex.data.api.network.model.PokemonDtoMapper
import com.floriantrecul.pokedex.data.api.network.service.PokedexService
import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonItem
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexService: PokedexService,
    private val mapper: PokemonDtoMapper
): PokemonRepository {

    override suspend fun getPokemons(): List<PokemonItem> {
        val pokemons = pokedexService.getPokemons(limit = 151, offset = 0).results
        return pokemons.map {mapper.mapToDomainModelPokemonItem(it) }
    }

    override suspend fun getPokemon(pokemonId: Int): Pokemon =
        mapper.mapToDomainModelPokemon(pokedexService.getPokemon(pokemonId), pokedexService.getPokemonSpecies(pokemonId))
}