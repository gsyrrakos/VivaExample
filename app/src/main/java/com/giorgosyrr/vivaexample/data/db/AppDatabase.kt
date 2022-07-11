package com.giorgosyrr.vivaexample.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.giorgosyrr.vivaexample.data.model.DataFromApi


@Database(entities = [DataFromApi::class], version = AppDatabase.VERSION)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "data.db"
        const val VERSION = 1
    }
    abstract fun dataDao(): DataDao
}