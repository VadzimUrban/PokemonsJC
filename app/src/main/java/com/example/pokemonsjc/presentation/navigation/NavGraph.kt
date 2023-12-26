package com.example.pokemonsjc.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemonsjc.presentation.pokemonDetailScreen.PokemonsDetailScreen
import com.example.pokemonsjc.presentation.pokemonsScreen.PokemonsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Pokemons.route) {

        composable(route = Screens.Pokemons.route) {
            PokemonsScreen(
                navController = navController
            )
        }

        composable(
            route = Screens.PokemonDetail.route,
            arguments = listOf(navArgument(name = POKEMON_DETAIL_ARGUMENT_KEY) {
                type = NavType.LongType
            })
        ) {
            val pokemonId = it.arguments?.getLong(POKEMON_DETAIL_ARGUMENT_KEY)

            pokemonId?.let { id ->
                PokemonsDetailScreen(
                    id, navController = navController
                )
            }

            Log.i("after call", "call id = $pokemonId")
        }
    }
}