package com.example.domain.usecase

import com.example.domain.cardrepo.CardRepository

class CardUseCase(private val cardRepository: CardRepository) {

    suspend fun getCardInfo(cardNumber: String) =
        cardRepository.getCardInfo(cardNumber)
}