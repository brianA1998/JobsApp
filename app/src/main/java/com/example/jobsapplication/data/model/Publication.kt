package com.example.jobsapplication.data.model

import java.util.*

data class Publication(
    val id: String = "",
    var created_at: Date? = null,
    val publication_image: String = "",
    val publication_description: String = "",
)