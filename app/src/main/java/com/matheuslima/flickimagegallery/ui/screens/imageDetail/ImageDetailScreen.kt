@file:OptIn(ExperimentalMaterial3Api::class)
package com.matheuslima.flickimagegallery.ui.screens.imageDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.material.textview.MaterialTextView
import com.matheuslima.flickimagegallery.ui.screens.components.FlickerAsyncImage
import com.matheuslima.flickimagegallery.ui.screens.components.ImageMetadata
import com.matheuslima.flickimagegallery.ui.screens.shared.SharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun ImageDetailScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier
) {
    val imageDetailViewModel = ImageDetailViewModel(sharedViewModel)

    val date by imageDetailViewModel.date.collectAsState()
    val tags by imageDetailViewModel.tags.collectAsState()

    if (imageDetailViewModel.image.isNotEmpty()) {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(
                    text = imageDetailViewModel.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }, navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }, actions = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                }
            })
        }) {
            ImageContent(modifier, imageDetailViewModel, date, tags)
        }
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.LightGray)) {
            Text(text = "Image not found")
        }
    }
}

@Composable
private fun ImageContent(
    modifier: Modifier,
    imageDetailViewModel: ImageDetailViewModel,
    date: String,
    tags: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        FlickerAsyncImage(
            metadata = ImageMetadata(
                model = imageDetailViewModel.image,
                contentDescription = imageDetailViewModel.contentDescription,
                contentScale = ContentScale.Crop
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(horizontal = 20.dp)
        )
        Spacer(Modifier.size(20.dp))
        Column(
            modifier = Modifier
                .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Text(text = "Author: ${imageDetailViewModel.author}")
            Text(text = "Date: Image was posted $date")
            Text(text = "Description:")
            AndroidView(factory = { MaterialTextView(it) }, update = {
                it.text =
                    imageDetailViewModel.description.value
            })
            Text(text = "Tags: $tags")
        }

    }
}