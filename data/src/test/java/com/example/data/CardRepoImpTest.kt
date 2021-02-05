package com.example.data

import com.example.domain.Result
import com.example.remote.RemoteDataSource
import com.example.remote.getApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class CardRepoImpTest {

    private lateinit var repoImp: CardRepoImp

    @Before
    fun setUp() {

        repoImp = CardRepoImp(RemoteDataSource(getApiService()))
    }

    @Test
    fun getCardInfo() = runBlocking {
        val data = repoImp.getCardInfo("45717360")
        assertThat(data.status, `is`(Result.Status.SUCCESS))
    }
}