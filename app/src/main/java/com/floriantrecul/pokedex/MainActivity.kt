package com.floriantrecul.pokedex

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.floriantrecul.pokedex.navigation.PokedexAppNavigator
import com.floriantrecul.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = Color.TRANSPARENT
            PokedexHome()
        }
    }
}

@Composable
fun PokedexHome() {
    PokedexTheme {
        PokedexAppNavigator()
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    PokedexTheme {
        PokedexHome()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    PokedexTheme(darkTheme = true) {
        PokedexHome()
    }
}