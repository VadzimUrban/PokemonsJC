package com.example.pokemonsjc.data.remoteDataSource.responsModels

import kotlinx.serialization.Serializable

@Serializable
data class Pokemons(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PokemonItem>,
)
