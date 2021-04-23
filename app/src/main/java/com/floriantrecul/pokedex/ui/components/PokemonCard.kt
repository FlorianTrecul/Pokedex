package com.floriantrecul.pokedex.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CatchingPokemon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PokemonCard() {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.Cyan,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = {}),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            PokemonImage(uri = "", modifier = Modifier.size(96.dp), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                PokemonName(name = "Bulbizarre")
                Spacer(modifier = Modifier.width(8.dp))
                Row {
                    PokemonIcon(Icons.Filled.CatchingPokemon)
                    PokemonIcon(Icons.Filled.CatchingPokemon)
                }
            }
        }
        PokemonId(id = "14")
    }
}

@Composable
private fun PokemonName(name: String?) {
    Text(
        text = name ?: "",
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
private fun PokemonId(id: String?) {
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


