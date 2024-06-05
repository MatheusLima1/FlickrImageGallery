package com.matheuslima.flickimagegallery.data.api

import com.matheuslima.flickimagegallery.data.response.entities.FlickrImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("photos_public.gne")
    suspend fun getImages(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("tags") text: String
    ): Response<FlickrImage>
}