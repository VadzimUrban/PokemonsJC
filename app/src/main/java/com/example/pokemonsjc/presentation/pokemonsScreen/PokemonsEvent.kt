package com.example.pokemonsjc.presentation.pokemonsScreen

sealed class PokemonsEvent {
    data object DeletePokemons : PokemonsEvent()
    data object UpdatePokemons : PokemonsEvent()
    data class SearchPokemon(val pokemonName: String) : PokemonsEvent()
    data object ContinueOffline : PokemonsEvent()
    data object OpenAlertDialog : PokemonsEvent()
    data object HideAlertDialog : PokemonsEvent()
    data object ReturnPokemons : PokemonsEvent()
    data object OpenSearch : PokemonsEvent()
    data object CloseSearch : PokemonsEvent()
    data class OnQueryChange(val searchQuery: String) : PokemonsEvent()
    data class OnSearch(val searchQuery: String): PokemonsEvent()
    data class OnActiveChange(val isSearchBarActive: Boolean): PokemonsEvent()
}