package com.ucb.usecases

import com.ucb.data.BookRepository
import com.ucb.data.NetworkResult
import com.ucb.domain.Book

class SearchBooks(
    val bookRepository: BookRepository
) {
    suspend fun invoke(title: String): NetworkResult<List<Book>> {
        return bookRepository.getBooks(title)
    }
}
