package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.floriantrecul.pokedex.ui.data.model.PokemonItemUiModel
import java.util.*

@Composable
fun PokemonCard(pokemon: PokemonItemUiModel) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.Cyan,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(4.dp)
            .clickable(onClick = {}),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            PokemonImage(
                uri = pokemon.imageUrl,
                modifier = Modifier.size(96.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                PokemonName(name = pokemon.name)
                Spacer(modifier = Modifier.width(8.dp))
                Row {
                    PokemonIcon(Icons.Filled.CatchingPokemon)
                    PokemonIcon(Icons.Filled.CatchingPokemon)
                }
            }
            PokemonId(id = pokemon.id)
        }
    }
}

@Composable
private fun PokemonName(name: String?) {
    Text(
        text = name?.capitalize(Locale.getDefault()) ?: "",
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colors.onSurface,
        ),
    )
}

@Composable
private fun PokemonIcon(icon: ImageVector) {
    Icon(icon, contentDescription = "Description")
}

@Composable
private fun PokemonId(id: Int?) {
    Text(
        text = "# %03d".format(id),
        //modifier = Modifier.drawOpacity(0.1f),
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSurface,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}


