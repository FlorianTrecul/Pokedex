package com.floriantrecul.pokedex.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.ui.components.PokemonId
import com.floriantrecul.pokedex.ui.components.PokemonImage
import com.floriantrecul.pokedex.ui.components.PokemonName
import com.floriantrecul.pokedex.util.PokemonDetailsTabs

@Composable
fun PokemonDetailsScreen(
    viewModel: PokemonDetailsViewModel,
    navigateBack: () -> Unit
) {
    val isFavorite by remember { viewModel.isFavorite }
    val selectedTab by remember { viewModel.selectedTab }

    Scaffold(
        topBar = {
            PokemonDetailsTopBar(
                navigateBack = navigateBack,
                isFavorite = isFavorite,
                manageFavorite = viewModel::manageFavorite
            )
        },
        content = {
            PokemonDetailsBackground(
                loadSelectedTab = viewModel::loadSelectedTab,
                selectedTab = selectedTab
            )
        }
    )
}

@Composable
fun PokemonDetailsTopBar(
    navigateBack: () -> Unit,
    isFavorite: Boolean,
    manageFavorite: () -> Unit
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
                    contentDescription = stringResource(id = R.string.top_app_bar_back)
                )
            }
        },
        actions = {
            IconButton(onClick = { manageFavorite() }) {
                if (isFavorite) {
                    Icon(
                        Icons.Default.Favorite,
                        stringResource(R.string.menu_save_favorite),
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        stringResource(R.string.menu_delete_favorite),
                        tint = Color.Red
                    )
                }
            }

        },
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    )
}

@Composable
fun PokemonDetailsBackground(
    loadSelectedTab: (PokemonDetailsTabs) -> Unit,
    selectedTab: Int
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Red)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            PokemonDetailsBody()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(2f)
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
fun PokemonDetailsBody() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.weight(2f)) {
                Column {
                    PokemonName(name = "pokemon.name")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Text(text = "Bonjoir")
                        Text(text = "Bonjoir")
                    }
                }
            }
            PokemonId(
                id = 13,
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
                uri = "pokemon.imageUrl",
                modifier = Modifier.size(164.dp),
                contentDescription = null
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

