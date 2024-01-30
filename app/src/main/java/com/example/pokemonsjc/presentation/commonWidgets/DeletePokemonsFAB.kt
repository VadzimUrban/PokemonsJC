package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun DeletePokemonsFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick, backgroundColor = MaterialTheme.colorScheme.tertiary
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete all Pokemons",
            tint = MaterialTheme.colorScheme.onTertiary
        )
    }
}