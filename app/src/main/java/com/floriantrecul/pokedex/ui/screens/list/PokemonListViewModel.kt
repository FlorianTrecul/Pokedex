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

package com.floriantrecul.pokedex.ui.screens.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floriantrecul.pokedex.data.repository.PokemonRepository
import com.floriantrecul.pokedex.ui.data.mapper.PokemonItemUiMapper
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel
import com.floriantrecul.pokedex.util.Constants.PAGE_SIZE
import com.floriantrecul.pokedex.util.Resource.Error
import com.floriantrecul.pokedex.util.Resource.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonItemUiMapper: PokemonItemUiMapper
) : ViewModel() {

    var pokemonList = mutableStateOf<List<PokemonItemUiModel>>(listOf())
    var loadError = mutableStateOf(0)
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var currentPage = 0

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            isLoading.value = true
            val result = pokemonRepository.getPokemons(PAGE_SIZE, currentPage * PAGE_SIZE)
            when (result) {
                is Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                is Success -> {
                    val count = pokemonRepository.getCount(PAGE_SIZE, currentPage * PAGE_SIZE)
                    endReached.value = currentPage * PAGE_SIZE >= count
                    val pokemonsItemUi = result.data.map { pokemonItemUiMapper.mapToDomainModelPokemonItemUi(it) }
                    currentPage++
                    loadError.value = 0
                    isLoading.value = false
                    pokemonList.value += pokemonsItemUi
                }
                else -> Unit
            }
        }
    }
}