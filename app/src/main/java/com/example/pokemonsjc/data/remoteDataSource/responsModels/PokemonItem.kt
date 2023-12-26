package com.example.pokemonsjc.data.remoteDataSource.responsModels

import kotlinx.serialization.Serializable

@Serializable
data class PokemonItem(
    val name: String,
    val url: String,
)
