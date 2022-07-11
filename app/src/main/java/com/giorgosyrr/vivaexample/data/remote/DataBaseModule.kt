package com.giorgosyrr.vivaexample.data.remote


import android.content.Context
import androidx.room.Room
import com.giorgosyrr.vivaexample.data.db.AppDatabase
import com.giorgosyrr.vivaexample.data.db.DataDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule  {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDatabase): DataDao {
        return appDataBase.dataDao()
    }
}