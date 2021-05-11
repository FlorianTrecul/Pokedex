package com.floriantrecul.pokedex.ui.screens.details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.floriantrecul.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    private var pokemonId: Int = 0

    var isFavorite = mutableStateOf(false)

    fun toInit(pokemonId: Int) {
        this.pokemonId = pokemonId
        Log.d("TAGAG", "pokemonId $pokemonId")
    }

    fun manageFavorite() {
        isFavorite.value = !isFavorite.value
    }
}