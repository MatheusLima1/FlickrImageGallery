package com.matheuslima.flickimagegallery.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        fontSize = 18.sp,
        modifier = modifier.padding(top = 5.dp)
    )
}