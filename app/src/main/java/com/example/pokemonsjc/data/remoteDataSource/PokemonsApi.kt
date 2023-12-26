package com.example.pokemonsjc.data.remoteDataSource

import com.example.pokemonsjc.data.remoteDataSource.responsModels.PokemonRemoteDB
import com.example.pokemonsjc.data.remoteDataSource.responsModels.Pokemons
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonsApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Pokemons


    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int,
    ): PokemonRemoteDB
}