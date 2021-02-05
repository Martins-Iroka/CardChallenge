package com.example.remote


class RemoteDataSource(private val apiService: ApiService) : ResponseResult() {

    suspend fun getCardInfo(cardNumber: String) = getResult { apiService.getCardInfo(cardNumber) }
}