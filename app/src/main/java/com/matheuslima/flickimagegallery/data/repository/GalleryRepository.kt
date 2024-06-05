package com.matheuslima.flickimagegallery.data.repository

import com.matheuslima.flickimagegallery.data.response.entities.FlickrImage
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    suspend fun getImages(text: String): Flow<FlickrImage>
}