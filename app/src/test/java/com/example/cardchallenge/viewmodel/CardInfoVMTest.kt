package com.example.cardchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cardchallenge.MainCoroutineRule
import com.example.cardchallenge.getOrAwaitValue
import com.example.data.CardRepoImp
import com.example.domain.usecase.CardUseCase
import com.example.remote.RemoteDataSource
import com.example.remote.getApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

import org.junit.Rule

@ExperimentalCoroutinesApi
class CardInfoVMTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var cardInfoVM: CardInfoVM

    @Before
    fun setUp() {
        val repo = CardRepoImp(RemoteDataSource(getApiService()))
        val cardUseCase = CardUseCase(repo)
        cardInfoVM = CardInfoVM(cardUseCase)
    }

    @Test
    fun getCardInfo() = runBlocking {
        cardInfoVM.cardNumber.value = "45717360"

        cardInfoVM.getCardInfo()
        val card = cardInfoVM.cardInfo.getOrAwaitValue()

        println(card)
        assertThat(card, `is`(notNullValue()))
    }
}