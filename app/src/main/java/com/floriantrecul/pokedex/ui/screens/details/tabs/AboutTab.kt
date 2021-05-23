package com.floriantrecul.pokedex.ui.screens.details.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.ui.components.PokemonInfoLabel
import com.floriantrecul.pokedex.ui.components.PokemonInfoText
import com.floriantrecul.pokedex.ui.components.PokemonInfoTitle
import com.floriantrecul.pokedex.ui.data.model.PokemonDetailsUiModel
import java.util.*
import kotlin.math.round

@Composable
fun AboutTab(pokemon: PokemonDetailsUiModel) {

    val labels: List<Int> =
        listOf(
            R.string.tab_about_label_height,
            R.string.tab_about_label_weight,
            R.string.tab_about_label_abilities
        )

    val pokemonHeightInMeters = remember { round(pokemon.height * 100f) / 1000f }
    val pokemonWeightInKg = remember { round(pokemon.weight * 100f) / 1000f }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            PokemonInfoTitle(title = R.string.tab_about_title_description)
            Text(
                text = pokemon.description.replace("\n", " "),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black.copy(0.7f),
                ),
            )
            PokemonInfoTitle(title = R.string.tab_about_title_various)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    items(labels) { label ->
                        PokemonInfoLabel(label = label)
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(2f)
                ) {
                    PokemonInfoText(text = "$pokemonHeightInMeters m")
                    PokemonInfoText(text = "$pokemonWeightInKg kg")
                    LazyRow() {
                        itemsIndexed(pokemon.abilities) { index, ability ->
                            if (index == pokemon.abilities.lastIndex) {
                                PokemonInfoText(text = ability.name.capitalize(Locale.ROOT))
                            } else {
                                PokemonInfoText(text = "${ability.name.capitalize(Locale.ROOT)}, ")
                            }
                        }
                    }
                }
            }
        }
    }
}