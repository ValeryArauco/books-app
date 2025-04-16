package com.ucb.data

import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.domain.Book
class BookRepository(val remoteDataSource: IBookRemoteDataSource) {
    suspend fun getBooks(title: String): NetworkResult<List<Book>>{
        return this.remoteDataSource.getBooks(title)
    }
}
