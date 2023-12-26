package com.example.pokemonsjc.data.remoteDataSource.responsModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
    @SerialName("front_default")
    val frontDefault: String?,
)
