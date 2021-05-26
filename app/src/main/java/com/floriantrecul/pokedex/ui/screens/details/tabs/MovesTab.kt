package com.floriantrecul.pokedex.ui.screens.details.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.floriantrecul.pokedex.ui.components.PokemonInfoText
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel
import com.floriantrecul.pokedex.ui.data.model.PokemonMovesUiModel
import java.util.*

@Composable
fun MovesTab(pokemon: PokemonDetailsUiModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            val itemCount = if (pokemon.moves.size % 2 == 0) {
                pokemon.moves.size / 2
            } else {
                pokemon.moves.size / 2 + 1
            }
            items(itemCount) {
                MoveRow(rowIndex = it, moves = pokemon.moves)
            }
        }
    }
}

@Composable
fun MoveRow(
    rowIndex: Int,
    moves: List<PokemonMovesUiModel>
) {
    Column {
        Row {
            PokemonInfoText(
                text = moves[rowIndex * 2].name.capitalize(Locale.ROOT),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (moves.size >= rowIndex * 2 + 2) {
                PokemonInfoText(
                    text = moves[rowIndex * 2 + 1].name.capitalize(Locale.ROOT),
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}