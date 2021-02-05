package com.example.domain.model

data class Card(
    private val scheme: String,
    private val type: String,
    private val brand: String,
    private val country: Country,
    private val bank: Bank
)

data class Country(
    private val name: String
)

data class Bank(
    private val name: String?
)
