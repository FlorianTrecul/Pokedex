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