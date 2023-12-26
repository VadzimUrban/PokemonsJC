package com.example.pokemonsjc.presentation.navigation

const val POKEMON_DETAIL_ARGUMENT_KEY = "id"

sealed class Screens(val route: String) {
    data object Pokemons : Screens("pokemons_screen")
    data object PokemonDetail : Screens("pokemon_detail_screen/{$POKEMON_DETAIL_ARGUMENT_KEY}") {
        fun passId(id: Long): String {
            return this.route.replace(
                oldValue = "{$POKEMON_DETAIL_ARGUMENT_KEY}", newValue = id.toString()
            )
        }
    }
}