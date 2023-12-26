package com.example.pokemonsjc.presentation.pokemonsScreen

sealed class PokemonsEvent {
    data object DeletePokemons : PokemonsEvent()
    data object UpdatePokemons : PokemonsEvent()
    data class SearchPokemon(val pokemonName: String) : PokemonsEvent()
    data object ContinueOffline: PokemonsEvent()
}