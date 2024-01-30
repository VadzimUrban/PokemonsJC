package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScrollToTopButton(
    showButton: Boolean,
    coroutineScope: CoroutineScope,
    listState: LazyListState,
) {
    AnimatedVisibility(visible = showButton) {
        Box {
            SmallFloatingActionButton(
                modifier = Modifier.align(Alignment.Center), onClick = {
                    coroutineScope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }, containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}