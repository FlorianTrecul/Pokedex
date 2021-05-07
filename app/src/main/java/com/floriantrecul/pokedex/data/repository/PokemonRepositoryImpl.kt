package com.floriantrecul.pokedex.data.repository

import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.data.api.network.mapper.PokemonDtoMapper
import com.floriantrecul.pokedex.data.api.network.service.PokedexService
import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonItem
import com.floriantrecul.pokedex.util.Resource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexService: PokedexService,
    private val mapper: PokemonDtoMapper
) : PokemonRepository {

    override suspend fun getPokemons(limit: Int, offset: Int): Resource<List<PokemonItem>> {
        val response = try {
            pokedexService.getPokemons(limit, offset).results
        } catch (e: Exception) {
            return Resource.Error(R.string.error_return_api)
        }
        return Resource.Success(mapper.toDomainList(response))
    }

    override suspend fun getPokemon(pokemonId: Int): Pokemon =
        mapper.mapToDomainModelPokemon(
            pokedexService.getPokemon(pokemonId),
            pokedexService.getPokemonSpecies(pokemonId)
        )

    override suspend fun getCount(limit: Int, offset: Int): Int =
        mapper.mapToDomainModelCount(pokedexService.getPokemons(limit, offset).count)
}