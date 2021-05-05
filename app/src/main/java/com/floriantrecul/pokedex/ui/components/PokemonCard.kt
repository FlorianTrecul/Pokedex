package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel

@Composable
fun PokemonCard(pokemon: PokemonItemUiModel) {
    Card(
        backgroundColor = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = {}),
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
            Row(modifier = Modifier.layoutId("typesRow")) {
                PokemonIcon(Icons.Filled.CatchingPokemon)
                PokemonIcon(Icons.Filled.CatchingPokemon)
            }
            PokemonId(
                id = pokemon.id,
                modifier = Modifier.layoutId("pokemonId")
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


