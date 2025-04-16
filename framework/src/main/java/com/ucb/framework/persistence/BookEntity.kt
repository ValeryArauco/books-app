package com.ucb.framework.persistence

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity( tableName = "libros")
data class BookEntity(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "author")
    var authors: String,
    @ColumnInfo(name = "alias")
    var year: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}