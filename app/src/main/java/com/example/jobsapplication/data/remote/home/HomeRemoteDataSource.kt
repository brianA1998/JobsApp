package com.example.jobsapplication.data.remote.home

import com.example.jobsapplication.data.model.Publication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class HomeRemoteDataSource {

    suspend fun getAllPublications(): Flow<Result<List<Publication>>> = callbackFlow {

    }


}