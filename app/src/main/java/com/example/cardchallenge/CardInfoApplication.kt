package com.example.cardchallenge

import android.app.Application
import com.example.domain.usecase.CardUseCase

class CardInfoApplication : Application() {

    val cardUseCase: CardUseCase
        get() = ServiceProvide.provideCardUseCase()

    override fun onCreate() {
        super.onCreate()
    }
}