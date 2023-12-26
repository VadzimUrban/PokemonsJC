package com.example.pokemonsjc.presentation.pokemonsScreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.pokemonsjc.presentation.commonWidgets.AlertDialogToDeletAll
import com.example.pokemonsjc.presentation.commonWidgets.DeletePokemonsFAB
import com.example.pokemonsjc.presentation.commonWidgets.EmptyScreen
import com.example.pokemonsjc.presentation.commonWidgets.ErrorScreen
import com.example.pokemonsjc.presentation.commonWidgets.ScrollToTopButton
import com.example.pokemonsjc.presentation.navigation.Screens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
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
    val openDialog = remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pokemons", style = MaterialTheme.typography.titleLarge
                    )
                }, backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
        },
        floatingActionButton = {
            Column {

                ScrollToTopButton(
                    showButton = showButton, coroutineScope = coroutineScope, listState = listState
                )
                Spacer(modifier = Modifier.size(8.dp))
                DeletePokemonsFAB {
                    openDialog.value = true
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
                items(pokemonsUiState.pokemons) {
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
            if (openDialog.value) {
                AlertDialogToDeletAll(onDismissRequest = { openDialog.value = false },
                    onConfirmation = {
                        viewModel.createEvent(PokemonsEvent.DeletePokemons)
                        openDialog.value = false
                        Toast.makeText(
                            context, "Pokemons were deleted", Toast.LENGTH_SHORT
                        ).show()
                    })
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PokemonsScreenPreview() {
    PokemonsScreen(navController = rememberNavController())
}