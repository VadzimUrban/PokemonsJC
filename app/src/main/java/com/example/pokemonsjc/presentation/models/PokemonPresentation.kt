package com.example.pokemonsjc.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonPresentation(
    val id: Long,
    val photo: String?,
    val name: String,
    val type: String,
) : Parcelable
