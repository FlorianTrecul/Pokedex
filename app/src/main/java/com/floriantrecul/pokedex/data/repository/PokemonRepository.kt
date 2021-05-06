package com.floriantrecul.pokedex.data.repository

import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonItem

interface PokemonRepository {

    suspend fun getPokemons(limit: Int, offset: Int): List<PokemonItem>

    suspend fun getPokemon(pokemonId: Int): Pokemon

    suspend fun getCount(limit: Int, offset: Int): Int
}