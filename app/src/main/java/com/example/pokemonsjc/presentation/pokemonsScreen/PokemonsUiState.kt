package com.example.pokemonsjc.presentation.pokemonsScreen

import com.example.pokemonsjc.presentation.models.PokemonPresentation

data class PokemonsUiState(
    val pokemons: List<PokemonPresentation> = emptyList(),
    val errorMessage: String = "",
    val isScreenIsEmpty: Boolean = true,
    val isLoading: Boolean = false,
    val searchResultPokemons: List<PokemonPresentation> = emptyList(),
)
