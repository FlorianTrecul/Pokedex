package com.floriantrecul.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun darkTheme() = darkColors(
    primary = gray900,
    onPrimary = white,
    secondary = gray900,
    onSecondary = gray900,
    background = gray900,
    surface = gray700,
    onBackground = white.copy(alpha = .8f),
    onSurface = white.copy(alpha = .8f),
)

@Composable
fun lightTheme() = lightColors(
    primary = white,
    onPrimary = gray600,
    secondary = white,
    onSecondary = gray900.copy(alpha = .8f),
    background = white,
    surface = white,
    onBackground = gray900.copy(alpha = .8f),
    onSurface = gray900.copy(alpha = .8f),
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