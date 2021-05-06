package com.floriantrecul.pokedex.ui.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floriantrecul.pokedex.data.repository.PokemonRepository
import com.floriantrecul.pokedex.ui.data.mapper.PokemonItemUiMapper
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel
import com.floriantrecul.pokedex.util.Constants.PAGE_SIZE
import com.floriantrecul.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonItemUiMapper: PokemonItemUiMapper
) : ViewModel() {

    private val pokemonListMutableState = mutableStateOf<Resource<List<PokemonItemUiModel>>>(Resource.Empty)
    val pokemonListState: Resource<List<PokemonItemUiModel>> by pokemonListMutableState

    var currentPage = 0

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        //pokemonListMutableState.value = Resource.Loading
        viewModelScope.launch {
            try {
                val count = pokemonRepository.getCount(PAGE_SIZE, currentPage * PAGE_SIZE)
                val pokemons = pokemonRepository.getPokemons(PAGE_SIZE, currentPage * PAGE_SIZE)
                val pokemonItemUi = pokemons.map { pokemonItemUiMapper.mapToDomainModelPokemonItemUi(it) }
                pokemonListMutableState.value = Resource.Success(pokemonItemUi)
            } catch (e: Exception) {
                pokemonListMutableState.value = Resource.Error(e.message)
            }
        }
    }

}