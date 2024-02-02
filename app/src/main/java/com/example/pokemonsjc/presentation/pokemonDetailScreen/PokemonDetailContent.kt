package com.example.pokemonsjc.presentation.pokemonDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemonsjc.presentation.commonWidgets.AppBar
import com.example.pokemonsjc.presentation.models.PokemonPresentationDetail

private const val KG = " kg"
private const val CM = " cm"

@Composable
fun PokemonDetailContent(
    pokemon: PokemonPresentationDetail,
    returnToPokemons: () -> Unit,
) {
    Scaffold(containerColor = MaterialTheme.colorScheme.background,
        topBar = {
        AppBar(
            title = "", imageVector = Icons.Filled.ArrowBack, iconClick = returnToPokemons
        )
    }) { innerPadding ->
        Surface() {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                Card(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    ), colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ), shape = RoundedCornerShape(
                        topStart = 0.dp, topEnd = 0.dp, bottomStart = 30.dp, bottomEnd = 30.dp
                    )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(innerPadding),

                        ) {
                        if (pokemon.photo == null) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                modifier = Modifier.size(300.dp),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            AsyncImage(
                                modifier = Modifier
                                    .size(300.dp)
                                    .align(Alignment.CenterHorizontally),
                                model = pokemon.photo,
                                contentDescription = "PokemonPhoto"
                            )
                        }
                        Text(
                            text = pokemon.name,
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.displaySmall,
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    PokemonDetailStatsRow(title = "Type:", value = pokemon.type)
                    Spacer(modifier = Modifier.size(8.dp))
                    PokemonDetailStatsRow(
                        title = "Height:", value = pokemon.height + CM
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    PokemonDetailStatsRow(
                        title = "Weight:", value = pokemon.weight + KG
                    )
                }

            }
        }
    }
}

//
//@Preview(
//    showBackground = true, showSystemUi = true
//)
//@Composable
//private fun PokemonDetailContentPreview() {
//    PokemonsJCTheme {
//        PokemonDetailContent(
//            appBarTitle = "Vadim",
//            pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/23.png",
//            pokemonType = "fire",
//            pokemonHeight = "183",
//            pokemonWeight = "90"
//        ) {
//
//        }
//    }
//}