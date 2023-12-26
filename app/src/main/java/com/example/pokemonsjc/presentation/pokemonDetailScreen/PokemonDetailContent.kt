package com.example.pokemonsjc.presentation.pokemonDetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokemonsjc.R
import com.example.pokemonsjc.presentation.commonWidgets.AppBar
import com.example.pokemonsjc.presentation.models.PokemonPresentationDetail

private const val KG = " kg"
private const val CM = " cm"

@Composable
fun PokemonDetailContent(
    pokemon: PokemonPresentationDetail,
    returnToPokemons: () -> Unit,
) {
    Scaffold(topBar = {
        AppBar(
            title = pokemon.name, imageVector = Icons.Filled.ArrowBack, iconClick = returnToPokemons
        )
    }) { innerPadding ->
        Surface() {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                if (pokemon.photo == null) {
                    Image(
                        painter = painterResource(id = R.drawable.pokemon_detail_empty_photo),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
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