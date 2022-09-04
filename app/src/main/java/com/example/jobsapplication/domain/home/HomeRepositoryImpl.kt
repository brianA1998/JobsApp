package com.example.jobsapplication.domain.home

import com.example.jobsapplication.data.model.Publication

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getAllPublications(): Result<List<Publication>> {
        TODO("Not yet implemented")
    }
}