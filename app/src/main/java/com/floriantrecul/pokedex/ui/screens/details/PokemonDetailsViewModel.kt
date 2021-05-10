package com.floriantrecul.pokedex.ui.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import com.floriantrecul.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {

    private var pokemonId: Int = 0

    fun toInit(pokemonId: Int) {
        this.pokemonId = pokemonId
        Log.d("TAGAG", "pokemonId $pokemonId")
    }
}