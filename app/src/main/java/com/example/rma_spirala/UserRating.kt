package com.example.rma_spirala

data class UserRating(
    override val username: String,
    override val timestamp: Long,
    val rating: Double
) : UserImpression()

