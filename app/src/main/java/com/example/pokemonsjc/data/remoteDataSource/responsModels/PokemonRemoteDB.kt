package com.example.pokemonsjc.data.remoteDataSource.responsModels

import kotlinx.serialization.Serializable

@Serializable
data class PokemonRemoteDB(
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int,
)