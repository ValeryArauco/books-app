package com.ucb.domain

data class Book (
    val title: String,
    val authors: List<String>? = null,
    val publishYear: String? = null
)