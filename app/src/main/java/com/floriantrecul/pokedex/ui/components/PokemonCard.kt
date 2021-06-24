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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel
import com.floriantrecul.pokedex.util.extension.getMainColor
import com.floriantrecul.pokedex.util.extension.getTypeIcon

@Composable
fun PokemonCard(pokemon: PokemonItemUiModel, navigatePokemonDetailsScreen: (Int) -> Unit) {
    Card(
        backgroundColor = colorResource(id = pokemon.types.first().getMainColor()),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = { navigatePokemonDetailsScreen(pokemon.id) }),
        elevation = 8.dp
    ) {
        ConstraintLayout(constraintSet = setupConstraints()) {
            PokemonImage(
                uri = pokemon.imageUrl,
                modifier = Modifier
                    .size(96.dp)
                    .layoutId("pokemonImage"),
                contentDescription = null
            )
            PokemonName(
                name = pokemon.name,
                modifier = Modifier.layoutId("pokemonName")
            )
            LazyRow(
                modifier = Modifier.layoutId("typesRow"),
            ) {
                items(pokemon.types) { icon ->
                    PokemonIcon(
                        icon = icon.getTypeIcon(),
                        contentDescription = pokemon.name
                    )
                }
            }
            PokemonId(
                id = pokemon.id,
                modifier = Modifier
                    .layoutId("pokemonId")
            )
        }
    }
}

fun setupConstraints(): ConstraintSet {
    return ConstraintSet {
        val pokemonImage = createRefFor("pokemonImage")
        val pokemonName = createRefFor("pokemonName")
        val typesRow = createRefFor("typesRow")
        val pokemonId = createRefFor("pokemonId")

        constrain(pokemonImage) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        constrain(pokemonName) {
            start.linkTo(pokemonImage.end, margin = 8.dp)
            top.linkTo(pokemonImage.top, margin = 8.dp)
        }
        constrain(typesRow) {
            start.linkTo(pokemonName.start)
            top.linkTo(pokemonName.bottom)
        }
        constrain(pokemonId) {
            end.linkTo(parent.end, margin = 8.dp)
            bottom.linkTo(parent.bottom)
        }

    }
}


