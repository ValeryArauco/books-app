package com.ucb.data.book

import com.ucb.domain.Book
import com.ucb.domain.Gitalias

interface IBookLocalDataSource {
    suspend fun saveBook(book: Book): Boolean
}