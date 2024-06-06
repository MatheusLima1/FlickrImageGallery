@file:OptIn(ExperimentalMaterial3Api::class)

package com.matheuslima.flickimagegallery.ui.screens.gallery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.matheuslima.flickimagegallery.data.response.entities.FlickrImage
import com.matheuslima.flickimagegallery.ui.screens.Routes
import com.matheuslima.flickimagegallery.ui.screens.components.FlickerAsyncImage
import com.matheuslima.flickimagegallery.ui.screens.components.ImageMetadata
import com.matheuslima.flickimagegallery.ui.screens.shared.SharedViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GalleryScreen(
    navController: NavHostController,
    galleryViewModel: GalleryViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    val query by galleryViewModel.query.collectAsState()
    val isSearching by galleryViewModel.isSearching.collectAsState()
    val flickerImages by galleryViewModel.images.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchBar(
                query = query,
                onQueryChange = galleryViewModel::onQueryChange,
                onSearch = galleryViewModel::onQueryChange,
                active = isSearching,
                onActiveChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                content = {
                    ProgressBar()
                }
            )
        }
    ) { innerPadding ->
        if (flickerImages.items.isNotEmpty()) {
            FlickerGridList(flickerImages, navController, innerPadding, sharedViewModel)
        } else {
            EmptyResult(innerPadding)
        }
    }
}

@Composable
private fun ProgressBar() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            strokeWidth = 10.dp
        )
    }
}

@Composable
private fun EmptyResult(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
    ) {
        Text(
            text = "No images found",
            modifier = Modifier
                .fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun FlickerGridList(
    flickerImages: FlickrImage,
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: SharedViewModel
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(flickerImages.items.size) { index ->
                val imageMetadata = ImageMetadata(
                    model = flickerImages.items[index].media?.m,
                    contentDescription = flickerImages.items[index].title,
                    clickEvent = {
                        viewModel.imageSelected.value = flickerImages.items[index]
                        navController.navigate(Routes.IMAGE_DETAILS)
                    }
                )
                FlickerAsyncImage(
                    modifier = Modifier.size(width = 180.dp, height = 200.dp),
                    metadata = imageMetadata
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding(), start = 10.dp, end = 10.dp)
    )
}