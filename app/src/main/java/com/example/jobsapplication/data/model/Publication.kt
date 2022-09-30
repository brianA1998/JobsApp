package com.example.jobsapplication.data.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Publication(
    @Exclude @JvmField
    val id: String = "",
    @ServerTimestamp
    var created_at: Date? = null,
    val publication_image: String = "",
    val publication_description: String = "",
)