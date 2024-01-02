package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemonsjc.presentation.pokemonsScreen.PokemonsEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonsSearchBar(
    query: String,
    onQueryChange: (query: String) -> Unit,
    onSearch: (query: String) -> Unit,

) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        query = query,
        onQueryChange = {
            onQueryChange(it)
        },
        onSearch = {
            viewModel.createEvent(PokemonsEvent.OnSearch(it))
            viewModel.createEvent(PokemonsEvent.SearchPokemon(pokemonsUiState.searchQuery))
        },
        active = pokemonsUiState.isSearchBarActive,
        onActiveChange = {
            viewModel.createEvent(PokemonsEvent.OnActiveChange(it))
        },
        placeholder = {
            Text(text = "Search...")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (pokemonsUiState.isSearchBarActive) {
                Icon(
                    modifier = Modifier.clickable {
                        viewModel.createEvent(PokemonsEvent.CloseSearch)
                    }, imageVector = Icons.Filled.Close, contentDescription = "Search"
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        tonalElevation = 20.dp
    ) {
        pokemonsUiState.lastQueries.forEach {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
                .clickable {
                    viewModel.createEvent(
                        PokemonsEvent.SearchPokemon(
                            it
                        )
                    )
                }) {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refresh"
                )
                Text(
                    text = it
                )
            }
        }
    }
}