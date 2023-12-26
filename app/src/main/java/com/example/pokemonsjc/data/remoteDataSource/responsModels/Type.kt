package com.example.pokemonsjc.data.remoteDataSource.responsModels

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val slot: Int,
    val type: TypeX,
)
