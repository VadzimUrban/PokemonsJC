package com.example.pokemonsjc.di

import com.example.pokemonsjc.domain.interactors.PokemonsInteractor
import com.example.pokemonsjc.domain.repositories.PokemonsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    companion object {
        @Provides
        fun providePokemonInteractor(pokemonRepository: PokemonsRepository): PokemonsInteractor =
            PokemonsInteractor(pokemonRepository)
    }
}