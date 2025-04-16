package com.ucb.framework.book

import android.content.Context
import com.ucb.data.book.IBookLocalDataSource
import com.ucb.data.git.ILocalDataSource
import com.ucb.domain.Book
import com.ucb.domain.Gitalias
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.mappers.toModel
import com.ucb.framework.persistence.AppBookRoomDatabase
import com.ucb.framework.persistence.AppRoomDatabase
import com.ucb.framework.persistence.IBookEntityDAO
import com.ucb.framework.persistence.IGitAccountDAO

class BookLocalDataSource(val context: Context) : IBookLocalDataSource {
    val bookEntityDAO: IBookEntityDAO = AppBookRoomDatabase.getBookDatabase(context).bookDAO()
    override suspend fun saveBook(book: Book): Boolean {
        bookEntityDAO.insert(book.toEntity())
        return true
    }
}