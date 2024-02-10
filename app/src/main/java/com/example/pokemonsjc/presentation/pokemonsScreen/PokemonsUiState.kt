package com.example.pokemonsjc.presentation.pokemonsScreen

import androidx.compose.runtime.Stable
import com.example.pokemonsjc.presentation.models.PokemonPresentation

@Stable
data class PokemonsUiState(
    val pokemons: List<PokemonPresentation> = emptyList(),
    val errorMessage: String = "",
    val isScreenIsEmpty: Boolean = true,
    val isLoading: Boolean = false,
    val searchResultPokemons: List<PokemonPresentation> = emptyList(),
    val isDialogOpen: Boolean = false,
    val searchQuery: String = "",
    val isSearchBarActive: Boolean = false,
    val lastQueries: MutableList<String> = mutableListOf(""),
    val isShowingSearchBar: Boolean = false,
    val isShowingAppBarTitle: Boolean = true,
    val isShowingSearchIcon: Boolean = true,
    val isShowingCloseSearchingIcon: Boolean = false,
    val isPokemonSearchIsEmpty: Boolean = false,
)
