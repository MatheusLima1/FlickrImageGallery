package com.matheuslima.flickimagegallery.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun FlickerAsyncImage(metadata: ImageMetadata, modifier: Modifier = Modifier) {
    AsyncImage(
        model = metadata.model,
        contentDescription = metadata.contentDescription,
        contentScale = metadata.contentScale,
        modifier = modifier
            .clickable {
                metadata.clickEvent.invoke()
            }
    )
}