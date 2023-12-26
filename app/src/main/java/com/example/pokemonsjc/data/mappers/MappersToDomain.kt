package com.example.pokemonsjc.data.mappers

import com.example.pokemonsjc.data.localDataSource.entities.PokemonLocalDataSource
import com.example.pokemonsjc.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<PokemonLocalDataSource>>.toDomain(): Flow<List<Pokemon>> = map {
    it.map { pokemonDataLocalDB ->
        with(pokemonDataLocalDB) {
            Pokemon(
                id, photo, name, type, height, weight
            )
        }
    }
}


fun PokemonLocalDataSource.toDomain(): Pokemon = Pokemon(
    id, photo, name, type, height, weight
)