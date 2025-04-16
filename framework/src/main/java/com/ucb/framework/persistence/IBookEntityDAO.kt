package com.ucb.framework.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucb.domain.Book

@Dao
interface IBookEntityDAO {

    @Query("SELECT * FROM libros")
    fun getFavoriteBooks(): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookEntity: BookEntity)

}