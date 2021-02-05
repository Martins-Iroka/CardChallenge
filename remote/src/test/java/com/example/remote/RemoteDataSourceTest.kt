package com.example.remote

import com.example.domain.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSource(getApiService())
    }

    @Test
    fun getCardInfo() = runBlocking {
        val remote = remoteDataSource.getCardInfo("5199110730084073")
        assertThat(remote.status, `is`(Result.Status.SUCCESS))
    }
}