package com.matheuslima.flickimagegallery.data.repository

import com.matheuslima.flickimagegallery.data.api.FlickrApi
import com.matheuslima.flickimagegallery.utils.FlickerMockedObject.mockErrorResponse
import com.matheuslima.flickimagegallery.utils.FlickerMockedObject.successResponse
import com.matheuslima.flickimagegallery.utils.TestDispatchers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GalleryRepositoryImplTest {
    private var api: FlickrApi = mockk()
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var reposistory: GalleryRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        reposistory = GalleryRepositoryImpl(api, testDispatchers)
    }

    @Test
    fun givenAnyTextValueWhenGetImagesThenReturnSuccessResponse()  = runTest{
        coEvery { api.getImages(text = any()) } returns successResponse
        val data = runBlocking { api.getImages(text = "test") }
        assertEquals(successResponse, data)
    }

    @Test
    fun givenAnErrorResponseWhenGetImagesThenReturnErrorResponse() = runTest{
        coEvery { api.getImages(text = any()) } returns mockErrorResponse
        val data = runBlocking { api.getImages(text = "test") }
        assertEquals(mockErrorResponse, data)
    }
}