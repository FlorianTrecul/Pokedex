package com.floriantrecul.pokedex.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.ui.components.CircularProgressLoader
import com.floriantrecul.pokedex.ui.components.PokemonCard
import com.floriantrecul.pokedex.ui.components.PokemonRetryButton
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    navigatePokemonDetailsScreen: (Int) -> Unit
) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    Surface(
        modifier = Modifier.fillMaxSize(),
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
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.pokemon_list_description),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            PokemonList(
                pokemons = pokemonList,
                endReached = endReached,
                isLoading = isLoading,
                loadPokemons = viewModel::loadPokemons,
                loadError = loadError,
                navigatePokemonDetailsScreen = navigatePokemonDetailsScreen
            )
        }
    }
}

@Composable
fun PokemonList(
    pokemons: List<PokemonItemUiModel>,
    endReached: Boolean,
    isLoading: Boolean,
    loadPokemons: () -> Unit,
    loadError: Int,
    navigatePokemonDetailsScreen: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(pokemons) { index, pokemon ->
            if (index == pokemons.lastIndex && !endReached && !isLoading) {
                loadPokemons()
            }
            PokemonCard(
                pokemon = pokemon,
                navigatePokemonDetailsScreen = navigatePokemonDetailsScreen
            )
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressLoader(
                modifier = Modifier.size(128.dp),
                color = MaterialTheme.colors.onSurface
            )
        }
        if (loadError != 0) {
            PokemonRetryButton(error = stringResource(id = loadError)) {
                loadPokemons()
            }
        }
    }
}
