package com.ucb.data

import com.ucb.data.book.IBookLocalDataSource
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.data.git.ILocalDataSource
import com.ucb.domain.Book
import com.ucb.domain.Gitalias

class BookRepository(val remoteDataSource: IBookRemoteDataSource, private val localDataSource: IBookLocalDataSource) {
    suspend fun getBooks(title: String): NetworkResult<List<Book>>{
        return this.remoteDataSource.getBooks(title)
    }

    suspend fun saveBook(book: Book): Boolean {
        this.localDataSource.saveBook(book)
        return true
    }
}
