package com.example.pokemonsjc.domain.repositories

import com.example.pokemonsjc.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonsRepository {
    fun getPokemonsStream(): Flow<List<Pokemon>>

    suspend fun updateCache()

    suspend fun deleteAll()

    fun searchPokemonStream(name: String): Flow<List<Pokemon>>

    suspend fun getSinglePokemon(id: Long): Pokemon
}