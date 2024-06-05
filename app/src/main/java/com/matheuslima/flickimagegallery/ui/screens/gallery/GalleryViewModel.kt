package com.matheuslima.flickimagegallery.ui.screens.gallery

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheuslima.flickimagegallery.data.repository.GalleryRepository
import com.matheuslima.flickimagegallery.data.response.entities.FlickrImage
import com.matheuslima.flickimagegallery.ui.screens.shared.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val defaultDispatchers: DispatcherProvider
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    private val _images = MutableStateFlow<FlickrImage>(FlickrImage())
    val images: StateFlow<FlickrImage> = _images

    @VisibleForTesting
    public fun getImages(text: String) {
        _isSearching.value = true
        viewModelScope.launch(defaultDispatchers.io) {
            galleryRepository.getImages(text).collectLatest { images ->
                _images.value = images
                _isSearching.value = false
            }
        }
    }

    fun onQueryChange(text: String) {
        _query.value = text
        getImages(text)
    }

    init {
        getImages("")
    }

}