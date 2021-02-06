package com.example.cardchallenge

import com.example.data.CardRepoImp
import com.example.domain.cardrepo.CardRepository
import com.example.domain.usecase.CardUseCase
import com.example.remote.RemoteDataSource
import com.example.remote.getApiService

object ServiceProvide {

    fun provideCardUseCase(): CardUseCase {
        val cardRepo = provideCardRepo()

        return CardUseCase(cardRepo)
    }

    private fun provideCardRepo(): CardRepository {
        val remoteDataSource = provideRemoteSource()

        return CardRepoImp(remoteDataSource)
    }

    private fun provideRemoteSource(): RemoteDataSource {
        val apiService = getApiService()

        return RemoteDataSource(apiService)
    }
}