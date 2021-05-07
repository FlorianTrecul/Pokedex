package com.floriantrecul.pokedex.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.ui.components.PokemonCard
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    pokemonDetailsScreen: (String) -> Unit
) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.pokemon_list_title),
                modifier = Modifier.padding(8.dp),
                color = Color.Red,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.pokemon_list_description),
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokemonList(
                pokemons = pokemonList,
                endReached = endReached,
                loadPokemons = viewModel::loadPokemons
            )
        }
    }
}

@Composable
fun PokemonList(
    pokemons: List<PokemonItemUiModel>,
    endReached: Boolean,
    loadPokemons: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(pokemons) { index, pokemon ->
            if (index == pokemons.lastIndex && !endReached) {
                loadPokemons()
            }
            PokemonCard(pokemon = pokemon)
        }
    }
}
