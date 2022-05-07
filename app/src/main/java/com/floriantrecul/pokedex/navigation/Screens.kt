package com.floriantrecul.pokedex.navigation

import com.floriantrecul.pokedex.util.Constants.POKEMON_DETAILS_SCREEN
import com.floriantrecul.pokedex.util.Constants.POKEMON_LIST_SCREEN

sealed class Screens(val route: String) {
    object PokemonListScreen : Screens(POKEMON_LIST_SCREEN)
    object PokemonDetailScreen : Screens("$POKEMON_DETAILS_SCREEN/{pokemonId}")
}