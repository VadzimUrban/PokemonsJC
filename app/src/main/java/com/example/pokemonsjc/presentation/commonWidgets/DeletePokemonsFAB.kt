package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun DeletePokemonsFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.Delete, contentDescription = "Delete all Pokemons"
        )
    }
}