package com.ucb.domain

data class Plan(
    val name: String,
    val oldPrice: String,
    val currentPrice: String,
    val dataAmount: String,
    val features: List<String>
)