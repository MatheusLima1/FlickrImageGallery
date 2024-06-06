package com.matheuslima.flickimagegallery.ui.screens.shared

import androidx.lifecycle.ViewModel
import com.matheuslima.flickimagegallery.data.response.entities.Item
import kotlinx.coroutines.flow.MutableStateFlow

class SharedViewModel: ViewModel() {
    val imageSelected = MutableStateFlow<Item>(Item())
}