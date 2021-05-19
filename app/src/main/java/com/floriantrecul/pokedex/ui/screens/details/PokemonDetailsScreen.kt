package com.floriantrecul.pokedex.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.ui.components.PokemonIcon
import com.floriantrecul.pokedex.ui.components.PokemonId
import com.floriantrecul.pokedex.ui.components.PokemonImage
import com.floriantrecul.pokedex.ui.components.PokemonName
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel
import com.floriantrecul.pokedex.util.PokemonDetailsTabs
import com.floriantrecul.pokedex.util.Resource
import com.floriantrecul.pokedex.util.extension.getMainColor
import com.floriantrecul.pokedex.util.extension.getTypeTagIcon

@Composable
fun PokemonDetailsStateScreen(
    viewModel: PokemonDetailsViewModel,
    navigateBack: () -> Unit,
) {
    val isFavorite by remember { viewModel.isFavorite }
    val selectedTab by remember { viewModel.selectedTab }

    when (val pokemonState = viewModel.pokemonState) {
        is Resource.Empty -> {
        }
        is Resource.Error -> {
        }
        is Resource.Loading -> {
        }
        is Resource.Success -> PokemonDetailsScreen(
            navigateBack = navigateBack,
            isFavorite = isFavorite,
            selectedTab = selectedTab,
            pokemon = pokemonState.data,
            manageFavorite = viewModel::manageFavorite,
            loadSelectedTab = viewModel::loadSelectedTab
        )
    }
}

@Composable
fun PokemonDetailsScreen(
    navigateBack: () -> Unit,
    isFavorite: Boolean,
    selectedTab: Int,
    pokemon: PokemonDetailsUiModel,
    manageFavorite: () -> Unit,
    loadSelectedTab: (PokemonDetailsTabs) -> Unit,
) {

    Scaffold(
        topBar = {
            PokemonDetailsTopBar(
                navigateBack = navigateBack,
                isFavorite = isFavorite,
                pokemon = pokemon,
                manageFavorite = manageFavorite
            )
        },
        content = {
            PokemonDetailsBackground(
                loadSelectedTab = loadSelectedTab,
                selectedTab = selectedTab,
                pokemon = pokemon
            )
        }
    )
}

@Composable
fun PokemonDetailsTopBar(
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

@Composable
fun PokemonDetailsBackground(
    loadSelectedTab: (PokemonDetailsTabs) -> Unit,
    selectedTab: Int,
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
            PokemonDetailsBody(
                pokemon = pokemon
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clip(shape = RoundedCornerShape(25.dp, 25.dp))
                .background(Color.White)
        ) {
            PokemonDetailsInformationTabs(
                loadSelectedTab = loadSelectedTab,
                selectedTab = selectedTab
            )
        }
    }
}

@Composable
fun PokemonDetailsBody(pokemon: PokemonDetailsUiModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.weight(2f)) {
                Column {
                    PokemonName(
                        name = pokemon.name,
                        textSize = 40.sp
                    )
                    LazyRow {
                        items(pokemon.types) { icon ->
                            PokemonIcon(
                                icon = icon.getTypeTagIcon(),
                                height = 100.dp,
                                width = 100.dp,
                                name = pokemon.name
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

@Composable
fun PokemonDetailsInformationTabs(
    loadSelectedTab: (PokemonDetailsTabs) -> Unit,
    selectedTab: Int
) {
    val (isSelectedTab) = remember { mutableStateOf(PokemonDetailsTabs.About) }
    val tabs = PokemonDetailsTabs.values()

    Column {
        TabRow(
            selectedTabIndex = isSelectedTab.ordinal,
            modifier = Modifier.padding(horizontal = 16.dp),
            backgroundColor = Color.Transparent,
            indicator = { },
            divider = { }
        ) {
            tabs.forEachIndexed { index, tab ->
                val isSelected = index == isSelectedTab.titleRes
                Tab(
                    selected = isSelected,
                    selectedContentColor = MaterialTheme.colors.onBackground,
                    unselectedContentColor = MaterialTheme.colors.onBackground.copy(
                        alpha = ContentAlpha.disabled
                    ),
                    onClick = { loadSelectedTab(tab) }
                ) {
                    val tabName = stringResource(id = tab.titleRes)
                    Text(
                        text = tabName,
                        modifier = Modifier.paddingFromBaseline(top = 64.dp),
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
        SelectedPokemonInformationTab(selectedTab)

    }
}

@Composable
fun SelectedPokemonInformationTab(selectedTab: Int) {
    when (selectedTab) {
        R.string.pokemon_detail_about_tab -> About()
        R.string.pokemon_detail_base_stats_tab -> BaseStats()
        R.string.pokemon_detail_moves_tab -> Moves()
    }
}

@Composable
fun About() {
    Text(text = "About")
}

@Composable
fun BaseStats() {
    Text(text = "BaseStats")
}

@Composable
fun Moves() {
    Text(text = "Moves")
}

