package com.floriantrecul.pokedex.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.floriantrecul.pokedex.ui.screens.details.PokemonDetailsScreen
import com.floriantrecul.pokedex.ui.screens.home.Destinations.POKEMON_DETAIL_SCREEN
import com.floriantrecul.pokedex.ui.screens.home.Destinations.POKEMON_LIST_SCREEN
import com.floriantrecul.pokedex.ui.screens.list.PokemonListScreen

private object Destinations {
    const val POKEMON_LIST_SCREEN = "pokemonListScreen"
    const val POKEMON_DETAIL_SCREEN = "pokemonDetailsScreen"
}

class Actions(navController: NavHostController) {
    val pokemonListScreen: () -> Unit = {
        navController.navigate(POKEMON_LIST_SCREEN)
    }
    val pokemonDetailsScreen: (String) -> Unit = { pokemonId ->
        navController.navigate("$POKEMON_DETAIL_SCREEN/$pokemonId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}

@Composable
fun PokedexHomeScreen(startDestination: String = POKEMON_LIST_SCREEN) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(POKEMON_LIST_SCREEN) {
            PokemonListScreen(pokemonDetailsScreen = actions.pokemonDetailsScreen)
        }
        composable("$POKEMON_DETAIL_SCREEN/{pokemonId}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonId = requireNotNull(backStackEntry.arguments?.getString("pokemonId"))
            PokemonDetailsScreen()
        }
    }
}