package com.example.pokemonsjc.presentation.pokemonsScreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pokemonsjc.R
import com.example.pokemonsjc.presentation.commonWidgets.AlertDialogToDeleteAll
import com.example.pokemonsjc.presentation.commonWidgets.DeletePokemonsFAB
import com.example.pokemonsjc.presentation.commonWidgets.EmptyScreen
import com.example.pokemonsjc.presentation.commonWidgets.ErrorScreen
import com.example.pokemonsjc.presentation.commonWidgets.ScrollToTopButton
import com.example.pokemonsjc.presentation.navigation.Screens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PokemonsScreen(
    navController: NavController,
    viewModel: PokemonsViewModel = hiltViewModel(),
) {
    val pokemonsUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(refreshing = pokemonsUiState.isLoading,
        onRefresh = { viewModel.createEvent(PokemonsEvent.UpdatePokemons) })

    val context = LocalContext.current
    val listState = rememberLazyListState()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }
    val coroutineScope = rememberCoroutineScope()

    var text by remember {
        mutableStateOf("")
    }

    var active by remember {
        mutableStateOf(false)
    }

    val items = remember {
        mutableStateListOf("")
    }

    var isShowingSearchBar by remember {
        mutableStateOf(false)
    }
    var isShowingTitle by remember {
        mutableStateOf(true)
    }
    var isShowingSearchIcon by remember {
        mutableStateOf(true)
    }

    var isShowingCloseSearching by remember {
        mutableStateOf(false)
    }


    // remove all logics of boolean variables to viewModel and create search bar

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(70.dp),
                title = {
                    if (isShowingTitle) {
                        Text(
                            text = "Pokemons", style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                actions = {
                    if (isShowingSearchIcon) {
                        Icon(
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    isShowingSearchBar = true
                                    isShowingTitle = false
                                    isShowingSearchIcon = false
                                    isShowingCloseSearching = true
                                }, imageVector = Icons.Filled.Search, contentDescription = ""
                        )
                    }
                },
                navigationIcon = {
                    if (isShowingCloseSearching) {
                        Icon(imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    viewModel.createEvent(PokemonsEvent.ReturnPokemons)
                                    isShowingCloseSearching = false
                                })
                    }
                },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            )
            if (isShowingSearchBar) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    query = text,
                    onQueryChange = {
                        text = it
                    },
                    onSearch = {
                        if (items[0] == "") {
                            items[0] = text
                        } else {
                            items.add(text)
                        }
                        viewModel.createEvent(PokemonsEvent.SearchPokemon(text))
                        active = false
                        text = ""
                        isShowingSearchBar = false
                        isShowingSearchIcon = true
                        isShowingTitle = true
                        isShowingCloseSearching = true
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                    },
                    placeholder = {
                        Text(text = "Search...")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    },
                    trailingIcon = {
                        if (active) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (text.isNotEmpty()) {
                                        text = ""
                                    } else {
                                        active = false
                                        isShowingSearchBar = false
                                        isShowingTitle = true
                                        isShowingSearchIcon = true
                                    }
                                }, imageVector = Icons.Filled.Close, contentDescription = "Search"
                            )
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = SearchBarDefaults.colors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    tonalElevation = 20.dp
                ) {
                    items.forEach {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                            .clickable {
                                viewModel.createEvent(PokemonsEvent.SearchPokemon(it))
                                active = false
                                isShowingSearchBar = false
                                isShowingSearchIcon = true
                                isShowingCloseSearching = true
                                isShowingTitle = true
                            }) {
                            Icon(
                                modifier = Modifier.padding(end = 10.dp),
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Refresh"
                            )
                            Text(text = it)
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            Column {

                ScrollToTopButton(
                    showButton = showButton, coroutineScope = coroutineScope, listState = listState
                )
                Spacer(modifier = Modifier.size(8.dp))
                DeletePokemonsFAB {
                    viewModel.createEvent(PokemonsEvent.OpenAlertDialog)
                }
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = listState
            ) {
                items(pokemonsUiState.pokemons, key = { pokemon -> pokemon.id }) {
                    PokemonsItem(pokemon = it) {
                        navController.navigate(route = Screens.PokemonDetail.passId(it.id))
                        Log.i("after navigate", "navigate id = ${it.id}")
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = pokemonsUiState.isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            // error and empty Screens
            Box(modifier = Modifier.align(Alignment.Center)) {
                if (pokemonsUiState.isScreenIsEmpty) {
                    EmptyScreen(
                        image = painterResource(id = R.drawable.refresh100),
                        title = "Screen is empty, swipe down to refresh",
                    )
                }
                if (pokemonsUiState.errorMessage != "") {
                    ErrorScreen(
                        image = painterResource(id = R.drawable.no_connection100),
                        title = "Something went wrong,",
                        subtitle = "check your internet connection",
                        buttonTitle = "Continue offline"
                    ) {
                        viewModel.createEvent(PokemonsEvent.ContinueOffline)
                    }
                }
            }

            //AlertDialog
            if (pokemonsUiState.isDialogOpen) {
                AlertDialogToDeleteAll(
                    onDismissRequest = { viewModel.createEvent(PokemonsEvent.HideAlertDialog) },
                    onConfirmation = {
                        viewModel.createEvent(PokemonsEvent.DeletePokemons)
                        viewModel.createEvent(PokemonsEvent.HideAlertDialog)
                        Toast.makeText(
                            context, "Pokemons were deleted", Toast.LENGTH_SHORT
                        ).show()
                    },
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PokemonsScreenPreview() {
    PokemonsScreen(navController = rememberNavController())
}