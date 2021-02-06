package com.example.domain.model

data class Card(
    val scheme: String,
    val type: String,
    val brand: String,
    val country: Country,
    val bank: Bank
)

data class Country(
    val name: String
)

data class Bank(
    val name: String?
)
