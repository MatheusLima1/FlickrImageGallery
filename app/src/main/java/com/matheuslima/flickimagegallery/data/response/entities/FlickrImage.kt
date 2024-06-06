package com.matheuslima.flickimagegallery.data.response.entities

data class FlickrImage(
    val description: String? = null,
    val generator: String? = null,
    val items: List<Item> = emptyList(),
    val link: String? = null,
    val modified: String? = null,
    val title: String? = null
)