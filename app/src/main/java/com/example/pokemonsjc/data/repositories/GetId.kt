package com.example.pokemonsjc.data.repositories

fun String.getId(link: String): String {
    var id = ""
    link.forEach {
        if (it.isDigit()) {
            id += it.toString()
        }
    }
    return id.drop(1)
}