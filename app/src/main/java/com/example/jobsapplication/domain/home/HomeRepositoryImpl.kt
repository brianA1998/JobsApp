package com.example.jobsapplication.domain.home

import com.example.jobsapplication.core.Result
import com.example.jobsapplication.data.model.Publication
import com.example.jobsapplication.data.remote.home.HomeRemoteDataSource
import kotlinx.coroutines.flow.Flow


class HomeRepositoryImpl(private val  dataSource : HomeRemoteDataSource) : HomeRepository {
    override suspend fun getAllPublications(): Flow<Result<List<Publication>>> {
        return dataSource.getAllPublications()
    }
}