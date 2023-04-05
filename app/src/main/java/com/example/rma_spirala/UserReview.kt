package com.example.rma_spirala

data class UserReview(
    override val username: String,
    override val timestamp: Long,
    val review: String
) : UserImpression() {

}

