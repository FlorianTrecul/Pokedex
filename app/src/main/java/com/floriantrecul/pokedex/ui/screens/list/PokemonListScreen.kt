package com.floriantrecul.pokedex.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.floriantrecul.pokedex.ui.components.PokemonCard
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel
import com.floriantrecul.pokedex.util.Resource.Empty
import com.floriantrecul.pokedex.util.Resource.Error
import com.floriantrecul.pokedex.util.Resource.Loading
import com.floriantrecul.pokedex.util.Resource.Success

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    pokemonDetailsScreen: (String) -> Unit
) {
    val pokemonListState = viewModel.pokemonListState
    Crossfade(targetState = pokemonListState) {
        when (pokemonListState) {
            is Empty -> {
            }
            is Error -> {
            }
            is Loading -> CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.scale(0.5f)
            )
            is Success -> PokemonList(pokemons = pokemonListState.data)
        }
    }
}

@Composable
fun PokemonList(pokemons: List<PokemonItemUiModel>) {
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Pokédex",
                color = Color.Red,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "The Pokédex contains detailed stats for every creature from the Pokémon games.",
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pokemons) { pokemon ->
                    PokemonCard(pokemon)
                }
            }
        }
    }
}