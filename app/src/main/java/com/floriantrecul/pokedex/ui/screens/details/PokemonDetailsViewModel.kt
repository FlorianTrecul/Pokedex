/* MIT License
 *
 * Copyright (c) 2021 Florian Trecul
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/

package com.floriantrecul.pokedex.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.data.repository.PokemonRepository
import com.floriantrecul.pokedex.ui.data.mapper.PokemonDetailsUiMapper
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel
import com.floriantrecul.pokedex.util.PokemonDetailsTabs
import com.floriantrecul.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonDetailsUiMapper: PokemonDetailsUiMapper
) : ViewModel() {

    private var pokemonId: Int = 0

    var isFavorite = mutableStateOf(false)

    private val pokemonMutableState = mutableStateOf<Resource<PokemonDetailsUiModel>>(Resource.Empty)
    val pokemonState: Resource<PokemonDetailsUiModel> by pokemonMutableState

    fun toInit(pokemonId: Int) {
        this.pokemonId = pokemonId
        loadPokemon(pokemonId)
    }

    private fun loadPokemon(pokemonId: Int) {
        pokemonMutableState.value = Resource.Loading
        viewModelScope.launch {
            try {
                val pokemon = pokemonRepository.getPokemon(pokemonId)
                val pokemonDetailsUi = pokemonDetailsUiMapper.mapToDomainModelPokemonDetailUi(pokemon)
                pokemonMutableState.value = Resource.Success(pokemonDetailsUi)
            } catch (e: Exception) {
                pokemonMutableState.value = Resource.Error(R.string.error_generic)
            }
        }
    }

    fun manageFavorite() {
        isFavorite.value = !isFavorite.value
    }
}