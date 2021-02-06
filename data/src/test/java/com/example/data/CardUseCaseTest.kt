package com.example.data

import com.example.domain.Result
import com.example.domain.cardrepo.CardRepository
import com.example.domain.usecase.CardUseCase
import com.example.remote.RemoteDataSource
import com.example.remote.getApiService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class CardUseCaseTest {

    private lateinit var repository: CardRepository
    private lateinit var cardUseCase: CardUseCase

    @Before
    fun setup() {
        val apiService = getApiService()
        val remoteDataSource = RemoteDataSource(apiService)
        repository = CardRepoImp(remoteDataSource)
        cardUseCase = CardUseCase(repository)
    }

    @Test
    fun getCardUseCase() = runBlocking {
        val card = cardUseCase.getCardInfo("5199110730084073")
        println(card.data)
        assertThat(card.status, `is`(Result.Status.SUCCESS))
    }
}