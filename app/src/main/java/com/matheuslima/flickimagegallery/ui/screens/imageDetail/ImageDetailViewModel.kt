package com.matheuslima.flickimagegallery.ui.screens.imageDetail

import android.text.Spanned
import androidx.annotation.VisibleForTesting
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.lifecycle.ViewModel
import com.matheuslima.flickimagegallery.ui.screens.shared.SharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

class ImageDetailViewModel (private val sharedViewModel: SharedViewModel) :
    ViewModel() {

    private val _date = MutableStateFlow(sharedViewModel.imageSelected.value.published ?: "")
    val date: StateFlow<String> = _date

    private val _description = MutableStateFlow<Spanned?>(null)
    val description: StateFlow<Spanned?> = _description

    private val _tags = MutableStateFlow(sharedViewModel.imageSelected.value.tags ?: "")
    val tags: StateFlow<String> = _tags

    val image = sharedViewModel.imageSelected.value.media?.m ?: ""
    val contentDescription = sharedViewModel.imageSelected.value.title ?: ""
    val title = sharedViewModel.imageSelected.value.title ?: TITLE_NOT_FOUND

    val author = sharedViewModel.imageSelected.value.author ?: UNKNOWN_AUTHOR

    @VisibleForTesting
    public fun parseDate() {
        val dateString = sharedViewModel.imageSelected.value.published
        val postedDate = LocalDateTime.ofInstant(Instant.parse(dateString), ZoneOffset.UTC)
        val currentDate = LocalDateTime.now(ZoneId.systemDefault())
        val daysAgo = ChronoUnit.DAYS.between(postedDate, currentDate)
        val daysAgoText: String = when (daysAgo) {
            0L -> {
                TODAY
            }

            1L -> {
                "$daysAgo $DAY_AGO"
            }

            else -> {
                "$daysAgo $DAYS_AGO"
            }
        }
        _date.value = daysAgoText
    }

    private fun parseTags() {

    }

    private fun parseDescription() {
        val description = sharedViewModel.imageSelected.value.description ?: ""
        _description.value = HtmlCompat.fromHtml(description, FROM_HTML_MODE_COMPACT)
    }

    init {
        parseDescription()
        parseTags()
        parseDate()
    }

    private companion object {
        private const val TODAY = "Today"
        private const val DAYS_AGO = "days ago"
        private const val DAY_AGO = "day ago"
        private const val UNKNOWN_AUTHOR = "Unknown Author"
        private const val TITLE_NOT_FOUND = "Title not found"
    }

}