package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScrollToTopButton(
    showButton: Boolean,
    coroutineScope: CoroutineScope,
    listState: LazyListState,
) {
    AnimatedVisibility(visible = showButton) {
        SmallFloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    listState.scrollToItem(index = 0)
                }
            },
        ) {
            Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null)
        }
    }
}