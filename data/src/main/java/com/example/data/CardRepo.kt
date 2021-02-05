package com.example.data

import com.example.domain.Result
import com.example.domain.cardrepo.CardRepository
import com.example.domain.model.Card
import com.example.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepoImp(private val remoteDataSource: RemoteDataSource) : CardRepository {

    override suspend fun getCardInfo(cardNumber: String): Result<Card?> {
        return withContext(Dispatchers.IO) {
            val cardInfo = remoteDataSource.getCardInfo(cardNumber)
            if (cardInfo.status == Result.Status.SUCCESS)
                Result.success(cardInfo.data)
            else Result.error(cardInfo.message)
        }
    }
}