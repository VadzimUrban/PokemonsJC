package com.example.pokemonsjc.data.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonsjc.data.localDataSource.entities.PokemonLocalDataSource

@Database(
    entities = [PokemonLocalDataSource::class],
    version = 2,

    )
abstract class PokemonsDataBase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonsDao
}