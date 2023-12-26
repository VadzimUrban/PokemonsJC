package com.example.pokemonsjc.presentation.pokemonDetailScreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun PokemonsDetailScreen(
    pokemonId: Long,
    navController: NavController,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {

    val pokemonsDetailUiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemonDetail(pokemonId = pokemonId)
    }

    with(pokemonsDetailUiState.pokemon) {
        PokemonDetailContent(
            pokemon = pokemonsDetailUiState.pokemon
        ) {
            navController.navigateUp()
        }

    }
}