package com.example.pokemonsjc.presentation.commonWidgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.pokemonsjc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, imageVector: ImageVector, iconClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title, style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { iconClick.invoke() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
                )) {
                Icon(imageVector = imageVector, contentDescription = stringResource(id = R.string.CDBack))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
    )
}