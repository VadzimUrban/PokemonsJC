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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokemonsjc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonsSearchBar(
    query: String,
    isSearchBarActive: Boolean,
    lastQueries: MutableList<String>,
    onQueryChange: (query: String) -> Unit,
    onSearch: (query: String) -> Unit,
    searchPokemon: (query: String) -> Unit,
    onActiveChange: (isActive: Boolean) -> Unit,
    closeSearch: () -> Unit,


    ) {
    SearchBar(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp),
        query = query,
        onQueryChange = {
            onQueryChange(it)
        },
        onSearch = {
            onSearch(it)
            searchPokemon(it)
        },
        active = isSearchBarActive,
        onActiveChange = {
            onActiveChange(it)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.Search), color = MaterialTheme.colorScheme.onSecondary
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.CDSearch),
                tint = MaterialTheme.colorScheme.onSecondary,
            )
        },
        trailingIcon = {
            if (isSearchBarActive) {
                Icon(
                    modifier = Modifier.clickable {
                        closeSearch()
                    },
                    imageVector = Icons.Filled.Close, contentDescription = stringResource(id = R.string.CDClose),
                    tint = MaterialTheme.colorScheme.onSecondary,
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondary,
            dividerColor = MaterialTheme.colorScheme.onSecondary,
            inputFieldColors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onSecondary
            )
        ),
        tonalElevation = 20.dp
    ) {
        lastQueries.forEach {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
                .clickable {
                    searchPokemon(it)
                }) {
                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = stringResource(id = R.string.CDSearchHistory)
                )
                Text(
                    text = it
                )
            }
        }
    }
}