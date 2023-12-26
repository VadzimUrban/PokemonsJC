package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    image: Painter,
    title: String,
    subtitle: String? = null,
    buttonTitle: String,
    continueOffline: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image, contentDescription = null
        )
        Spacer(modifier = Modifier.size(34.dp))
        Text(
            text = title,
        )
        if (subtitle != null) {
            Text(
                text = subtitle,
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = continueOffline) {
            Text(
                text = buttonTitle,
            )
        }
    }
}