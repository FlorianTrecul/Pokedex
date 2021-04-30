package com.floriantrecul.pokedex.data.api.network.service

import com.floriantrecul.pokedex.data.api.network.model.PokemonDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonPaginatedDto
import com.floriantrecul.pokedex.data.api.network.model.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonPaginatedDto

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): PokemonDto

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: Int
    ): PokemonSpeciesDto

}