package com.example.pokemonsjc.di

import android.content.Context
import androidx.room.Room
import com.example.pokemonsjc.data.localDataSource.PokemonsDao
import com.example.pokemonsjc.data.localDataSource.PokemonsDataBase
import com.example.pokemonsjc.data.repositories.PokemonsRepositoryImpl
import com.example.pokemonsjc.domain.repositories.PokemonsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

private const val DATA_BASE_NAME = "pokes.db"

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataBaseModule {

    @Binds
    fun bindPokemonsRepository(
        pokemonsRepository: PokemonsRepositoryImpl<Any?>,
    ): PokemonsRepository

    companion object {
        @Singleton
        @Provides
        fun providePokemonDatabase(@ApplicationContext appContext: Context): PokemonsDataBase {
            return Room.databaseBuilder(
                appContext, PokemonsDataBase::class.java, DATA_BASE_NAME
            ).build()
        }

        @Singleton
        @Provides
        fun providePokemonDao(pokemonsDataBase: PokemonsDataBase): PokemonsDao {
            return pokemonsDataBase.getPokemonDao()
        }

        @Provides
        fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}