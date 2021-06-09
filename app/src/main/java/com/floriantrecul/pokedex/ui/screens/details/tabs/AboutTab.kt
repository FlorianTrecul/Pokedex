package com.floriantrecul.pokedex.ui.screens.details.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.floriantrecul.pokedex.R
import com.floriantrecul.pokedex.ui.components.PokemonInfoLabel
import com.floriantrecul.pokedex.ui.components.PokemonInfoText
import com.floriantrecul.pokedex.ui.components.PokemonInfoTitle
import com.floriantrecul.pokedex.ui.data.model.PokemonAboutUiModel
import java.util.*
import kotlin.math.round

@Composable
fun AboutTab(pokemonAbout: PokemonAboutUiModel) {

    val labels: List<Int> =
        listOf(
            R.string.tab_about_label_height,
            R.string.tab_about_label_weight,
            R.string.tab_about_label_abilities
        )

    val pokemonHeightInMeters = remember { round(pokemonAbout.height * 100f) / 1000f }
    val pokemonWeightInKg = remember { round(pokemonAbout.weight * 100f) / 1000f }

    Column {
        PokemonInfoTitle(title = R.string.tab_about_title_description)
        Text(
            text = pokemonAbout.description.replace("\n", " "),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
        )
        PokemonInfoTitle(title = R.string.tab_about_title_various)
        Row(verticalAlignment = Alignment.CenterVertically) {
            LazyColumn(modifier = Modifier.weight(0.1f)) {
                items(labels) { label ->
                    PokemonInfoLabel(
                        label = label,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )
                }
            }
            Column(modifier = Modifier.weight(0.2f)) {
                PokemonInfoText(
                    text = "$pokemonHeightInMeters m",
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
                PokemonInfoText(
                    text = "$pokemonWeightInKg kg",
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
                LazyRow(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
                    itemsIndexed(pokemonAbout.abilities) { index, ability ->
                        if (index == pokemonAbout.abilities.lastIndex) {
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