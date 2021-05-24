package com.floriantrecul.pokedex.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.floriantrecul.pokedex.data.model.PokemonStat
import com.floriantrecul.pokedex.util.Constants.ANIMATION_DELAY
import com.floriantrecul.pokedex.util.Constants.ANIMATION_DURATION
import com.floriantrecul.pokedex.util.Constants.STAT_MAX_VALUE
import com.floriantrecul.pokedex.util.extension.getStatColor

@Composable
fun PokemonBaseStatsBar(pokemonStat: PokemonStat, modifier: Modifier = Modifier) {
    var animationPlayed by remember { mutableStateOf(false) }

    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) pokemonStat.value / STAT_MAX_VALUE else 0f,
        animationSpec = tween(
            ANIMATION_DURATION,
            ANIMATION_DELAY
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    LinearProgressIndicator(
        progress = currentPercent.value,
        color = colorResource(id = pokemonStat.value.getStatColor())
    )
}