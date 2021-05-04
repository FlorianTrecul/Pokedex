package com.floriantrecul.pokedex.ui.screens.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.floriantrecul.pokedex.data.repository.PokemonRepository
import com.floriantrecul.pokedex.ui.data.mapper.PokemonItemUiMapper
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel
import com.floriantrecul.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonItemUiMapper: PokemonItemUiMapper
) : ViewModel() {

    init {
        getPokemons()
    }

    private val pokemonListMutableState = mutableStateOf<Resource<List<PokemonItemUiModel>>>(Resource.Empty)
    val pokemonListState: Resource<List<PokemonItemUiModel>> by pokemonListMutableState

    private fun getPokemons() {
        //pokemonListMutableState.value = Resource.Loading
        viewModelScope.launch {
            try {
                val pokemons = pokemonRepository.getPokemons()
                val pokemonItemUi = pokemons.map { pokemonItemUiMapper.mapToDomainModelPokemonItemUi(it) }
                Log.d("TAG", "pokemons ${pokemons[0]} && pokemonItemUi ${pokemonItemUi[0]}")
                pokemonListMutableState.value = Resource.Success(pokemonItemUi)
            } catch (e: Exception) {
                pokemonListMutableState.value = Resource.Error(e.message)
            }
        }
    }

}