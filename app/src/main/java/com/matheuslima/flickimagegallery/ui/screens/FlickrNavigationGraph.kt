package com.matheuslima.flickimagegallery.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matheuslima.flickimagegallery.ui.screens.gallery.GalleryScreen
import com.matheuslima.flickimagegallery.ui.screens.imageDetail.ImageDetailScreen
import com.matheuslima.flickimagegallery.ui.screens.shared.SharedViewModel

@Composable
fun FlickrNavigationGraph() {
    val navController = rememberNavController()
    val sharedViewModel = remember { SharedViewModel() }

    NavHost(navController = navController, startDestination = Routes.GALLERY) {
        composable(Routes.GALLERY) {
            GalleryScreen(navController, sharedViewModel = sharedViewModel)
        }
        composable(Routes.IMAGE_DETAILS) {
            ImageDetailScreen(navController, sharedViewModel = sharedViewModel)
        }
    }
}