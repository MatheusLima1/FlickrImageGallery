package com.matheuslima.flickimagegallery.data.repository

import android.util.Log
import com.matheuslima.flickimagegallery.data.api.FlickrApi
import com.matheuslima.flickimagegallery.data.response.entities.FlickrImage
import com.matheuslima.flickimagegallery.ui.screens.shared.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val flickrApi: FlickrApi,
    private val defaultDispatchers: DispatcherProvider
): GalleryRepository {
    override suspend fun getImages(text: String): Flow<FlickrImage> = flow {
        flickrApi.getImages(text = text).body()?.let {
            emit(it)
        }
    }.catch { e ->
        Log.e("Error", e.message.toString())
    }.flowOn(defaultDispatchers.io)
}