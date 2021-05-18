package com.floriantrecul.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.floriantrecul.pokedex.navigation.Destinations.POKEMON_DETAILS_SCREEN
import com.floriantrecul.pokedex.navigation.Destinations.POKEMON_LIST_SCREEN
import com.floriantrecul.pokedex.ui.screens.details.PokemonDetailsStateScreen
import com.floriantrecul.pokedex.ui.screens.details.PokemonDetailsViewModel
import com.floriantrecul.pokedex.ui.screens.list.PokemonListScreen
import com.floriantrecul.pokedex.ui.screens.list.PokemonListViewModel

private object Destinations {
    const val POKEMON_LIST_SCREEN = "pokemonListScreen"
    const val POKEMON_DETAILS_SCREEN = "pokemonDetailsScreen"
}

class Actions(navController: NavHostController) {
    val navigateToPokemonListScreen: () -> Unit = {
        navController.navigate(POKEMON_LIST_SCREEN)
    }
    val navigateToPokemonDetailsScreen: (Int) -> Unit = { pokemonId ->
        navController.navigate("$POKEMON_DETAILS_SCREEN/$pokemonId")
    }
    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }
}

@Composable
fun PokedexAppNavigator(startDestination: String = POKEMON_LIST_SCREEN) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(POKEMON_LIST_SCREEN) {
            val pokemonListViewModel: PokemonListViewModel = navController.hiltNavGraphViewModel(
                route = POKEMON_LIST_SCREEN
            )
            PokemonListScreen(
                viewModel = pokemonListViewModel,
                navigatePokemonDetailsScreen = actions.navigateToPokemonDetailsScreen
            )
        }
        composable(
            "$POKEMON_DETAILS_SCREEN/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.IntType
                },
            )
        ) { backStackEntry ->
            val pokemonDetailsViewModel: PokemonDetailsViewModel = navController.hiltNavGraphViewModel(
                route = "$POKEMON_DETAILS_SCREEN/{pokemonId}"
            )
            val pokemonId = requireNotNull(backStackEntry.arguments?.getInt("pokemonId"))
            PokemonDetailsStateScreen(
                viewModel = pokemonDetailsViewModel.also { it.toInit(pokemonId) },
                navigateBack = actions.navigateBack
            )
        }
    }
}