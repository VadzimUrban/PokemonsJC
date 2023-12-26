package com.example.pokemonsjc.data.remoteDataSource

import com.example.pokemonsjc.data.localDataSource.entities.PokemonLocalDataSource
import com.example.pokemonsjc.data.remoteDataSource.responsModels.PokemonRemoteDB

fun PokemonRemoteDB.toLocalDb(): PokemonLocalDataSource = PokemonLocalDataSource(
    id = id.toLong(),
    photo = sprites.frontDefault,
    name = name,
    type = types[0].type.name,
    height = height.toString(),
    weight = weight.toString()
)