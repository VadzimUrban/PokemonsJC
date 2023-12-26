package com.example.pokemonsjc.domain.models

data class Pokemon(
    val id: Long,
    val photo: String?,
    val name: String,
    val type: String,
    val height: String,
    val weight: String,
)
