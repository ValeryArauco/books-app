package com.ucb.framework.book

import com.ucb.data.NetworkResult
import com.ucb.data.book.IBookRemoteDataSource
import com.ucb.data.git.IGitRemoteDataSource
import com.ucb.data.movie.IMovieRemoteDataSource
import com.ucb.domain.Book
import com.ucb.domain.Gitalias
import com.ucb.domain.Movie
import com.ucb.framework.mappers.toModel
import com.ucb.framework.service.RetrofitBuilder

class BookRemoteDataSource(
    val retrofiService: RetrofitBuilder
):IBookRemoteDataSource {
    override suspend fun getBooks(title: String): NetworkResult<List<Book>>{
        val response = retrofiService.booksService.searchBooks(title)
        if (response.isSuccessful){
            return NetworkResult.Success(response.body()!!.docs.map { it.toModel() })
        }else{
            return NetworkResult.Error(response.message())
        }
    }
}

