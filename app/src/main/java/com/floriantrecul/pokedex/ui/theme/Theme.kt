package com.floriantrecul.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun darkTheme() = darkColors(
    primary = gray900,
    onPrimary = gray900,
    secondary = gray900,
    background = gray900,
    surface = gray700,
    onBackground = white,
    onSurface = white,
)

@Composable
fun lightTheme() = lightColors(
    primary = white,
    onPrimary = gray900,
    secondary = white,
    background = white,
    surface = white,
    onBackground = white,
    onSurface = gray900
)

@Composable
fun PokedexTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        darkTheme()
    } else {
        lightTheme()
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}