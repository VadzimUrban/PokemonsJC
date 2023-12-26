package com.example.pokemonsjc.data.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemonsjc.data.localDataSource.entities.PokemonLocalDataSource
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonsDao {
    @Query("SELECT * FROM pokemons")
    fun getPokemonsStream(): Flow<List<PokemonLocalDataSource>>

    @Insert(entity = PokemonLocalDataSource::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemon(vararg pokemonLocalDataSource: PokemonLocalDataSource)

    @Query("DELETE FROM pokemons")
    suspend fun deleteAll()

    @Query("SELECT * FROM pokemons WHERE name LIKE '%' || :name || '%' ")
    fun searchPokemonsStream(name: String): Flow<List<PokemonLocalDataSource>>

    @Query("SELECT * FROM pokemons WHERE id LIKE :id")
    suspend fun getSinglePokemon(id: Long): PokemonLocalDataSource
}


