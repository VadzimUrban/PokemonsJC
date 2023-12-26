package com.example.pokemonsjc.domain.interactors

import com.example.pokemonsjc.domain.models.Pokemon
import com.example.pokemonsjc.domain.repositories.PokemonsRepository
import kotlinx.coroutines.flow.Flow

class PokemonsInteractor(private val pokemonsRepository: PokemonsRepository) {

    fun getPokemonsStream(): Flow<List<Pokemon>> = pokemonsRepository.getPokemonsStream()

    fun searchPokemonStream(name: String): Flow<List<Pokemon>> =
        pokemonsRepository.searchPokemonStream(name)

    suspend fun updateCache() = pokemonsRepository.updateCache()

    suspend fun clearCache() = pokemonsRepository.deleteAll()

    suspend fun getSinglePokemon(id: Long): Pokemon = pokemonsRepository.getSinglePokemon(id)
}