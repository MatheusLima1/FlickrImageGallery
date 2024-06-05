package com.matheuslima.flickimagegallery.utils

import com.matheuslima.flickimagegallery.data.response.entities.FlickrImage
import com.matheuslima.flickimagegallery.data.response.entities.Item
import com.matheuslima.flickimagegallery.data.response.entities.Media
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.time.Instant
import java.util.Date

object FlickerMockedObject {
    private val publishedAtOneDayAgo = "2023-06-01T12:00:00Z"
    private val publishedAtToday = Date.from(Instant.now())
    private val publishedAtTwoDayAgo = "2023-06-01T12:00:00Z"
    private val errorResponse =
        "{\n" +
                "  \"type\": \"error\",\n" +
                "  \"message\": \"What you were looking for isn't here.\"\n" +
                "}"
    private val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
    private val items = listOf(
        Item(
            title = "test",
            media = Media(m = "https://example.com/image.jpg"),
            tags = "test test test test test",
            author = "test",
            published = publishedAtToday.toString()
        ),
        Item(title = "test", media = null),
        Item(title = "test", media = null),
        Item(title = "test", media = null),
        Item(title = "test", media = null),
        Item(title = "test", media = null),
    )
    val successResponse = Response.success(FlickrImage(items = items))
    val mockErrorResponse = Response.error<FlickrImage>(400, errorResponseBody)
}