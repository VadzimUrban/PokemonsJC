package com.example.pokemonsjc.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonsjc.presentation.navigation.NavGraph

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun PokemonsApplication() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}