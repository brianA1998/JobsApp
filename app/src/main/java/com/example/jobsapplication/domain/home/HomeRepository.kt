package com.example.jobsapplication.domain.home

import com.example.jobsapplication.data.model.Publication
import kotlinx.coroutines.flow.Flow
import com.example.jobsapplication.core.Result

interface HomeRepository {

    suspend fun getAllPublications(): Flow<Result<List<Publication>>>

}