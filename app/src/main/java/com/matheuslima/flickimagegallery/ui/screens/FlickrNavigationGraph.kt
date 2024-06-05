package com.matheuslima.flickimagegallery.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matheuslima.flickimagegallery.ui.screens.gallery.GalleryScreen
import com.matheuslima.flickimagegallery.ui.screens.imageDetail.ImageDetailScreen

@Composable
fun FlickrNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.GALLERY) {
        composable(Routes.GALLERY) {
            GalleryScreen(navController)
        }
        composable(Routes.IMAGE_DETAILS) {
            ImageDetailScreen(navController)
        }
    }
}