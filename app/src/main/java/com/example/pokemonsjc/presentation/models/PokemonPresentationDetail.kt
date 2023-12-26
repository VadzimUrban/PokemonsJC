package com.example.pokemonsjc.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonPresentationDetail(
    val id: Long,
    val photo: String?,
    val name: String,
    val type: String,
    val height: String,
    val weight: String,
) : Parcelable