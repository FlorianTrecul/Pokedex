package com.floriantrecul.pokedex.data.repository

import com.floriantrecul.pokedex.data.model.Pokemon
import com.floriantrecul.pokedex.data.model.PokemonItem

interface PokemonRepository {

    suspend fun getPokemons(): List<PokemonItem>

    suspend fun getPokemon(pokemonId: Int): Pokemon
}