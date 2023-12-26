package com.example.pokemonsjc.presentation.pokemonDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonsjc.domain.interactors.PokemonsInteractor
import com.example.pokemonsjc.presentation.mappers.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonsInteractor: PokemonsInteractor,
) : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonDetailUiState> = MutableStateFlow(
        PokemonDetailUiState()
    )
    val uiState = _uiState.asStateFlow()
    fun getPokemonDetail(pokemonId: Long) = viewModelScope.launch {
        _uiState.update {
            it.copy(pokemon = pokemonsInteractor.getSinglePokemon(pokemonId).toPresentation())
        }
    }
}
