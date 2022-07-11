package com.giorgosyrr.vivaexample.data.db


import androidx.room.*
import com.giorgosyrr.vivaexample.data.model.DataFromApi
import io.reactivex.Maybe

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(data: List<DataFromApi>): List<Long>

    @Delete
    fun deleteItem(data: DataFromApi): Int

    @Query("SELECT * from DataFromApi")
    fun selectAllData(): Maybe<List<DataFromApi>>

    @Query("DELETE FROM DataFromApi")
    fun deleteAll()

}