package com.example.pokemonsjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pokemonsjc.presentation.PokemonsApplication
import com.example.pokemonsjc.ui.theme.PokemonsJCTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonsJCTheme {
               PokemonsApplication()
            }
        }
    }
}