package com.matheuslima.flickimagegallery.di

import com.matheuslima.flickimagegallery.data.api.FlickrApi
import com.matheuslima.flickimagegallery.data.repository.GalleryRepository
import com.matheuslima.flickimagegallery.data.repository.GalleryRepositoryImpl
import com.matheuslima.flickimagegallery.ui.screens.shared.DefaultDispatchers
import com.matheuslima.flickimagegallery.ui.screens.shared.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val client = OkHttpClient().newBuilder().apply {
            addInterceptor(interceptor = interceptor)
            readTimeout(60, TimeUnit.SECONDS)
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/feeds/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesFlickrApi(retrofit: Retrofit): FlickrApi {
        return retrofit.create(FlickrApi::class.java)
    }

    @Singleton
    @Provides
    fun providesDefaultDispatchers(): DispatcherProvider {
        return DefaultDispatchers()
    }

    @Provides
    fun provideGalleryRepository(
        flickrApi: FlickrApi,
        dispatchers: DispatcherProvider
    ): GalleryRepository = GalleryRepositoryImpl(flickrApi, dispatchers)
}