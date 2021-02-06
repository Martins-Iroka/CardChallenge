package com.example.cardchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cardchallenge.MainCoroutineRule
import com.example.cardchallenge.ServiceProvide
import com.example.cardchallenge.getOrAwaitValue
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
        val cardUseCase = ServiceProvide.provideCardUseCase()
        cardInfoVM = CardInfoVM(cardUseCase)
    }

    @Test
    fun getCardInfo() = runBlocking {
        cardInfoVM.cardNumber.value = "45717360"

        cardInfoVM.getCard()
        val card = cardInfoVM.cardInfo.getOrAwaitValue()

        println(card)
        assertThat(card, `is`(notNullValue()))
    }
}