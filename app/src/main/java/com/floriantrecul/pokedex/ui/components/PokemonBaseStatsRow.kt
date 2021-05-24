package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.floriantrecul.pokedex.data.model.PokemonStat
import com.floriantrecul.pokedex.util.extension.toBaseStatResId

@Composable
fun PokemonBaseStatsRow(pokemonStat: PokemonStat, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokemonInfoLabel(
            label = pokemonStat.name.toBaseStatResId(),
            modifier = Modifier.weight(.15f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        PokemonInfoText(
            text = pokemonStat.value.toString(),
            modifier = Modifier.weight(.1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        PokemonBaseStatsBar(pokemonStat = pokemonStat, modifier = Modifier.weight(.35f))
    }
}