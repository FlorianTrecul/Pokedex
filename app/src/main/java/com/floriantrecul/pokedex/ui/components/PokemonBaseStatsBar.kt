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
import com.floriantrecul.pokedex.ui.data.model.PokemonBaseStatsUiModel
import com.floriantrecul.pokedex.util.Constants.ANIMATION_DELAY
import com.floriantrecul.pokedex.util.Constants.ANIMATION_DURATION
import com.floriantrecul.pokedex.util.Constants.STAT_MAX_VALUE
import com.floriantrecul.pokedex.util.extension.getStatColor

@Composable
fun PokemonBaseStatsBar(pokemonBaseStatsUiModel: PokemonBaseStatsUiModel, modifier: Modifier = Modifier) {
    var animationPlayed by remember { mutableStateOf(false) }

    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) pokemonBaseStatsUiModel.value / STAT_MAX_VALUE else 0f,
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
        color = colorResource(id = pokemonBaseStatsUiModel.value.getStatColor())
    )
}