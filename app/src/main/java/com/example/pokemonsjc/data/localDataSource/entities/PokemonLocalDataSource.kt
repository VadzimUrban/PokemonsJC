package com.example.pokemonsjc.data.localDataSource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val POKEMONS_TABLE_NAME = "pokemons"

@Entity(tableName = POKEMONS_TABLE_NAME)
data class PokemonLocalDataSource(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("id") val id: Long,
    @ColumnInfo("photo") val photo: String?,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("height") val height: String,
    @ColumnInfo("weight") val weight: String,
)
