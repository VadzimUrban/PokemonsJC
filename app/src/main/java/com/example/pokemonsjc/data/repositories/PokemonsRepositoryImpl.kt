package com.example.pokemonsjc.data.repositories

import com.example.pokemonsjc.data.localDataSource.PokemonsDao
import com.example.pokemonsjc.data.mappers.toDomain
import com.example.pokemonsjc.data.remoteDataSource.PokemonsApi
import com.example.pokemonsjc.data.remoteDataSource.toLocalDb
import com.example.pokemonsjc.domain.repositories.PokemonsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonsRepositoryImpl<Pokemon> @Inject constructor(
    private val pokemonsDao: PokemonsDao,
    private val pokemonsApi: PokemonsApi,
    private val dispatcherIO: CoroutineDispatcher,
) : PokemonsRepository {

    override fun getPokemonsStream(): Flow<List<com.example.pokemonsjc.domain.models.Pokemon>> {
        val pokemons = pokemonsDao.getPokemonsStream().flowOn(dispatcherIO)
        return pokemons.toDomain()
    }

    override suspend fun deleteAll() = withContext(dispatcherIO) {
        pokemonsDao.deleteAll()
    }

    override fun searchPokemonStream(name: String): Flow<List<com.example.pokemonsjc.domain.models.Pokemon>> {
        val pokemons = pokemonsDao.searchPokemonsStream(name).flowOn(dispatcherIO)
        return pokemons.toDomain()
    }

    override suspend fun getSinglePokemon(id: Long): com.example.pokemonsjc.domain.models.Pokemon =
        withContext(dispatcherIO) {
            pokemonsDao.getSinglePokemon(id).toDomain()
        }

    override suspend fun updateCache() = withContext(dispatcherIO) {
        var id: String
        for (pokemonItem in getPokemonsResponse().await()) {
            id = pokemonItem.url.getId(pokemonItem.url)
            pokemonsDao.insertPokemon(
                pokemonsApi.getPokemon(id = id.toInt()).toLocalDb()
            )
        }
    }

    private suspend fun getPokemonsResponse() = withContext(dispatcherIO) {
        async {
            pokemonsApi.getPokemons(0, getPokemonsCount().await() - 1).results
        }
    }

    private suspend fun getPokemonsCount() = withContext(dispatcherIO) {
        async {
            pokemonsApi.getPokemons(0, 1).count
        }
    }
}