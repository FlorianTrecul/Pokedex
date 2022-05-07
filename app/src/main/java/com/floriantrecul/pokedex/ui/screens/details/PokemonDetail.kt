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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.ui.components.*
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel
import com.floriantrecul.pokedex.ui.screens.details.tabs.TabItem
import com.floriantrecul.pokedex.util.Resource
import com.floriantrecul.pokedex.util.extension.getMainColor
import com.floriantrecul.pokedex.util.extension.getTypeTagIcon
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonDetailScreen(
    navigateBack: () -> Unit,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val isFavorite by remember { viewModel.isFavorite }

    when (val pokemonState = viewModel.pokemonState) {
        is Resource.Empty -> {
        }
        is Resource.Error -> {
        }
        is Resource.Loading -> PokemonProgressLoader()
        is Resource.Success -> PokemonDetail(
            navigateBack = navigateBack,
            isFavorite = isFavorite,
            pokemon = pokemonState.data,
            manageFavorite = viewModel::manageFavorite
        )
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonDetail(
    navigateBack: () -> Unit,
    isFavorite: Boolean,
    pokemon: PokemonDetailsUiModel,
    manageFavorite: () -> Unit
) {

    Scaffold(
        topBar = {
            PokemonDetailTopBar(
                navigateBack = navigateBack,
                isFavorite = isFavorite,
                pokemon = pokemon,
                manageFavorite = manageFavorite
            )
        },
        content = {
            PokemonDetailBackground(pokemon = pokemon)
        }
    )
}

@Composable
fun PokemonDetailTopBar(
    navigateBack: () -> Unit,
    isFavorite: Boolean,
    pokemon: PokemonDetailsUiModel,
    manageFavorite: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "",
                color = MaterialTheme.colors.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.top_app_bar_back),
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { manageFavorite() }) {
                if (isFavorite) {
                    Icon(
                        Icons.Default.Favorite,
                        stringResource(R.string.menu_save_favorite),
                        tint = Color.White
                    )
                } else {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        stringResource(R.string.menu_delete_favorite),
                        tint = Color.White
                    )
                }
            }

        },
        elevation = 0.dp,
        backgroundColor = colorResource(id = pokemon.types.first().getMainColor())
    )
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonDetailBackground(
    pokemon: PokemonDetailsUiModel
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(
                colorResource(
                    id = pokemon.types
                        .first()
                        .getMainColor()
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            PokemonDetailBody(
                pokemon = pokemon
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clip(shape = RoundedCornerShape(25.dp, 25.dp))
                .background(MaterialTheme.colors.background)
        ) {
            PokemonDetailInformationTabs(pokemon = pokemon)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PokemonDetailBody(pokemon: PokemonDetailsUiModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.weight(2f)) {
                Column {
                    PokemonName(
                        name = pokemon.name,
                        style = MaterialTheme.typography.h2,
                    )
                    LazyRow {
                        items(pokemon.types) { icon ->
                            PokemonIcon(
                                icon = icon.getTypeTagIcon(),
                                height = 100.dp,
                                width = 100.dp,
                                contentDescription = pokemon.name
                            )
                        }
                    }
                }
            }
            PokemonId(
                id = pokemon.id,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokemonImage(
                uri = pokemon.imageUrl,
                modifier = Modifier.size(350.dp),
                contentDescription = pokemon.name
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun PokemonDetailInformationTabs(
    pokemon: PokemonDetailsUiModel
) {
    val pokemonInformationTabs = listOf(
        TabItem.About(pokemon.pokemonAbout),
        TabItem.BaseStats(pokemon.stats),
        TabItem.Moves(pokemon.moves)
    )
    val pagerState = rememberPagerState(pageCount = pokemonInformationTabs.size)

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        PokemonInformationTab(pagerState, pokemonInformationTabs) {
            selectedTabIndex = it
        }
        Spacer(modifier = Modifier.height(2.dp))
        pokemonInformationTabs[selectedTabIndex].screen()
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun PokemonInformationTab(
    pagerState: PagerState,
    pokemonInformationTabs: List<TabItem>,
    onTabSelected: (selectedIndex: Int) -> Unit,
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        pokemonInformationTabs.forEachIndexed { index, tabItem ->
            Tab(
                text = {
                    Text(
                        text = stringResource(id = tabItem.title),
                        color = MaterialTheme.colors.onPrimary
                    )
                },
                selected = selectedTabIndex == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            )
        }
    }
}