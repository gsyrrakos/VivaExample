package com.giorgosyrr.vivaexample.data.repository




import com.giorgosyrr.vivaexample.data.model.DataFromApi
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface DataRepository {
    val listBoardFirstData: Observable<List<DataFromApi>>
    fun insertData(data: List<DataFromApi>): Disposable
    val databaseSelect: Maybe<List<DataFromApi>>

    fun deleteAllUsers()


}