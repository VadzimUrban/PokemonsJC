package com.example.pokemonsjc.presentation.pokemonDetailScreen

import com.example.pokemonsjc.presentation.models.PokemonPresentationDetail

data class PokemonDetailUiState(
    val pokemon: PokemonPresentationDetail = PokemonPresentationDetail(0, "", "", "", "", ""),
)
