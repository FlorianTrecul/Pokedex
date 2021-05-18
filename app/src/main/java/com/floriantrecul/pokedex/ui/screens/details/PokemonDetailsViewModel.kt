package com.floriantrecul.pokedex.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    var loadError = mutableStateOf(0)
    var isFavorite = mutableStateOf(false)
    var selectedTab = mutableStateOf(PokemonDetailsTabs.About.titleRes)

    private val pokemonMutableState = mutableStateOf<Resource<PokemonDetailsUiModel>>(Resource.Empty())
    val pokemonState: Resource<PokemonDetailsUiModel> by pokemonMutableState

    fun toInit(pokemonId: Int) {
        this.pokemonId = pokemonId
        loadPokemon(pokemonId)
    }

    private fun loadPokemon(pokemonId: Int) {
        viewModelScope.launch {
            when (val result = pokemonRepository.getPokemon(pokemonId)) {
                is Resource.Empty -> {}
                is Resource.Loading -> {}
                is Resource.Error -> {
                    loadError.value = result.message!!
                }
                is Resource.Success -> {
                    val pokemonDetailsUi = pokemonDetailsUiMapper.mapToDomainModelPokemonDetailUi(result.data)
                    pokemonMutableState.value = Resource.Success(pokemonDetailsUi)
                }
            }
        }
    }

    fun manageFavorite() {
        isFavorite.value = !isFavorite.value
    }

    fun loadSelectedTab(tab: PokemonDetailsTabs) {
        selectedTab.value = tab.titleRes
    }
}