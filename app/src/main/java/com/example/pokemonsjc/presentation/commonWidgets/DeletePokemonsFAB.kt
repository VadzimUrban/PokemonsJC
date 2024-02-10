package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.pokemonsjc.R

@Composable
fun DeletePokemonsFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick, backgroundColor = MaterialTheme.colorScheme.tertiary
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.CDDeleteAllPokemons),
            tint = MaterialTheme.colorScheme.onTertiary
        )
    }
}