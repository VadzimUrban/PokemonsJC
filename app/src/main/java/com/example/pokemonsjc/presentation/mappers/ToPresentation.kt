package com.example.pokemonsjc.presentation.mappers

import com.example.pokemonsjc.domain.models.Pokemon
import com.example.pokemonsjc.presentation.models.PokemonPresentation
import com.example.pokemonsjc.presentation.models.PokemonPresentationDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<Pokemon>>.toPresentationStream(): Flow<List<PokemonPresentation>> = map {
    it.map { pokemon ->
        with(pokemon) {
            PokemonPresentation(id, photo, name.replaceFirstChar { name ->
                name.uppercase()
            }, type)
        }
    }
}

fun Pokemon.toPresentation(): PokemonPresentationDetail =
    PokemonPresentationDetail(id, photo, name.replaceFirstChar { name ->
        name.uppercase()
    }, type, height, weight)
