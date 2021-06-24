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

package com.floriantrecul.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
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
            val pokemonListViewModel = hiltViewModel<PokemonListViewModel>(
                navController.getBackStackEntry(route = POKEMON_LIST_SCREEN)
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
            val pokemonDetailsViewModel = hiltViewModel<PokemonDetailsViewModel>(
                navController.getBackStackEntry(route = POKEMON_LIST_SCREEN)
            )
            val pokemonId = requireNotNull(backStackEntry.arguments?.getInt("pokemonId"))
            PokemonDetailsStateScreen(
                viewModel = pokemonDetailsViewModel.also { it.toInit(pokemonId) },
                navigateBack = actions.navigateBack
            )
        }
    }
}