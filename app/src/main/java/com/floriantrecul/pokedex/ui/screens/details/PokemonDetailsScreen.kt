package com.floriantrecul.pokedex.ui.screens.details

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.floriantrecul.pokedex.R

@Composable
fun PokemonDetailsScreen(
    viewModel: PokemonDetailsViewModel,
    navigateBack: () -> Unit
) {
    val isFavorite by remember { viewModel.isFavorite }

    Scaffold(
        topBar = {
            PokemonDetailsScreenTopBar(
                navigateBack = navigateBack,
                isFavorite = isFavorite,
                manageFavorite = viewModel::manageFavorite
            )
        },
        content = {}
    )
}

@Composable
fun PokemonDetailsScreenTopBar(
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