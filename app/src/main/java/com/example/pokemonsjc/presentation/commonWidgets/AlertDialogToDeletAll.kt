package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun AlertDialogToDeletAll(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,

    ) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        text = "Are you sure ?",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Box(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    Row(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        TextButton(
                            onClick = onDismissRequest, modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Dismiss")
                        }
                        TextButton(
                            onClick = onConfirmation, modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Confirm")
                        }
                    }
                }
            }
        }
    }
}
