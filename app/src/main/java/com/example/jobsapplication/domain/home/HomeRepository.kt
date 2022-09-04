package com.example.jobsapplication.domain.home

import com.example.jobsapplication.data.model.Publication

interface HomeRepository {

    suspend fun getAllPublications() : Result<List<Publication>>

}