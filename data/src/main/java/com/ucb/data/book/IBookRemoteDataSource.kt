package com.ucb.data.book

import com.ucb.data.NetworkResult
import com.ucb.domain.Book

interface IBookRemoteDataSource {
    suspend fun getBooks(title: String): NetworkResult<List<Book>>
}