package com.example.domain.cardrepo

import com.example.domain.Result
import com.example.domain.model.Card

interface CardRepository {

    suspend fun getCardInfo(cardNumber: String): Result<Card?>
}