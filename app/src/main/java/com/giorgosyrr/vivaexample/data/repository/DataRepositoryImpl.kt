package com.giorgosyrr.vivaexample.data.repository


import android.annotation.SuppressLint
import android.util.Log
import android.util.Log.d
import com.giorgosyrr.vivaexample.data.db.AppDatabase
import com.giorgosyrr.vivaexample.data.model.DataFromApi
import com.giorgosyrr.vivaexample.data.remote.ApiManager
import com.giorgosyrr.vivaexample.di.custom_annotation.PerFragment
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@PerFragment
class DataRepositoryImpl @Inject constructor(apiManager: ApiManager, database: AppDatabase) :
    DataRepository {
    private val apiManager: ApiManager = apiManager
    private val database: AppDatabase = database
    override val listBoardFirstData: Observable<List<DataFromApi>>
        get() = apiManager.listBoardData

    override val databaseSelect = database.dataDao().selectAllData()
    override fun insertData(data: List<DataFromApi>): Disposable =

        Observable
            .fromCallable { database.dataDao().insertItem(data) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                d("TAG", "item added: subscribe:$it")
            }

    @SuppressLint("CheckResult")
    override fun deleteAllUsers() {
        Observable
            .fromCallable { database.dataDao().deleteAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                d("TAG", "database rows cleared:: subscribe:$it")
            }

    }
}