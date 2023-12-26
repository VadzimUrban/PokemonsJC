package com.example.pokemonsjc.presentation.pokemonsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonsjc.domain.interactors.PokemonsInteractor
import com.example.pokemonsjc.presentation.mappers.toPresentationStream
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DOWNLOAD_ERROR = "Download error"

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonsInteractor: PokemonsInteractor,
) : ViewModel() {
    private val _uiState: MutableStateFlow<PokemonsUiState> = MutableStateFlow(PokemonsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            pokemonsInteractor.getPokemonsStream().toPresentationStream().catch { e ->
                _uiState.update {
                    it.copy(errorMessage = e.toString())
                }
                Log.e(DOWNLOAD_ERROR, e.toString())
            }.collect { pokemons ->
                if (pokemons.isEmpty()) {
                    _uiState.update { pokemonsUiState ->
                        pokemonsUiState.copy(errorMessage = "")
                    }
                } else {
                    _uiState.update { pokemonsUiState ->
                        pokemonsUiState.copy(pokemons = pokemons, isScreenIsEmpty = false)
                    }
                }
            }
        }
    }

    fun createEvent(event: PokemonsEvent) = onEvent(event)

    private fun onEvent(event: PokemonsEvent) = viewModelScope.launch {
        when (event) {
            PokemonsEvent.DeletePokemons -> {
                pokemonsInteractor.clearCache()

                _uiState.update {
                    it.copy(
                        pokemons = emptyList(),
                        errorMessage = "",
                        isScreenIsEmpty = true,
                    )
                }
                // dont work hz why
                this.coroutineContext.cancelChildren()
            }

            is PokemonsEvent.SearchPokemon -> {
                pokemonsInteractor.searchPokemonStream(event.pokemonName).toPresentationStream()
                    .collect {
                        _uiState.update { pokemonsUiState ->
                            pokemonsUiState.copy(
                                pokemons = it, errorMessage = ""
                            )
                        }
                    }
            }

            PokemonsEvent.UpdatePokemons -> {
                try {
                    pokemonsInteractor.updateCache()
                    _uiState.update {
                        it.copy(errorMessage = "")
                    }
                } catch (e: Throwable) {
                    _uiState.update {
                        it.copy(
                            pokemons = emptyList(),
                            errorMessage = e.toString(),
                            isScreenIsEmpty = false
                        )
                    }
                    Log.e(DOWNLOAD_ERROR, e.toString())
                }
            }

            PokemonsEvent.ContinueOffline -> {
                pokemonsInteractor.getPokemonsStream().toPresentationStream().collect { pokemons ->
                    if (pokemons.isEmpty()) {
                        _uiState.update { pokemonsUiState ->
                            pokemonsUiState.copy(errorMessage = "", isScreenIsEmpty = true)
                        }
                    } else {
                        _uiState.update {
                            it.copy(isScreenIsEmpty = false, errorMessage = "", pokemons = pokemons)
                        }
                    }
                }
            }
        }
    }
}