package com.example.jobsapplication.ui.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.collect
import com.example.jobsapplication.core.Result
import com.example.jobsapplication.domain.home.HomeRepository
import kotlinx.coroutines.Dispatchers

class HomeScreenViewModel(private val repo: HomeRepository) : ViewModel() {
    fun getAllPublications() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        kotlin.runCatching {
            repo.getAllPublications()
        }.onSuccess { flowList ->
            flowList.collect {
                emit(it)
            }

        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }


    class Factory(private val repo: HomeRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(HomeRepository::class.java).newInstance(repo)
        }
    }

}

