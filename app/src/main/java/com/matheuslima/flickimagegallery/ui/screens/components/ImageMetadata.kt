package com.matheuslima.flickimagegallery.ui.screens.components

import androidx.compose.ui.layout.ContentScale

data class ImageMetadata(
    val model: String? = null,
    val contentDescription: String? = null,
    val clickEvent: () -> Unit = {},
    val contentScale: ContentScale = ContentScale.FillHeight
)