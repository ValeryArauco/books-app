package com.ucb.framework.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [BookEntity::class], version = 2, exportSchema = false)
abstract class AppBookRoomDatabase : RoomDatabase() {
    abstract fun bookDAO(): IBookEntityDAO

    companion object {
        @Volatile
        var Instance: AppBookRoomDatabase? = null

        fun getBookDatabase(context: Context): AppBookRoomDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppBookRoomDatabase::class.java, "libros")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}