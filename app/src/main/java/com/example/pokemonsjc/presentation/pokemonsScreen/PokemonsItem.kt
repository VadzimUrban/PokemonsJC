package com.example.pokemonsjc.presentation.pokemonsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemonsjc.R
import com.example.pokemonsjc.presentation.models.PokemonPresentation
import com.example.pokemonsjc.ui.theme.PokemonsJCTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonsItem(pokemon: PokemonPresentation, openPokemonDetailScreen: () -> Unit) {
    Card(
        onClick = openPokemonDetailScreen,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)

    ) {
        Row(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            if (pokemon.photo == null) {
                Icon(
                    imageVector = Icons.Filled.Person, modifier = Modifier.size(80.dp),
                    contentDescription = stringResource(id = R.string.CDPokemonsItemDefaultPhoto), tint = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                AsyncImage(
                    model = pokemon.photo,
                    contentDescription = stringResource(id = R.string.CDPokemonsItemPhoto),
                    modifier = Modifier.size(80.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pokemon.name,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Text(
                    text = pokemon.type,
                    modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                )
            }
        }
    }
}


@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
private fun PokemonsItemPreview() {
    PokemonsJCTheme {
        PokemonsItem(
            pokemon = PokemonPresentation(
                1,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
                "s",
                "fire",
            )
        ) {}
    }
}


