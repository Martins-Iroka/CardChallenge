package com.example.remote

import com.example.domain.model.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://lookup.binlist.net/"

interface ApiService {

    @GET("{cardNum}")
    suspend fun getCardInfo(@Path("cardNum") cardNum: String): Response<Card>
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun getApiService(): ApiService = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build().create(ApiService::class.java)