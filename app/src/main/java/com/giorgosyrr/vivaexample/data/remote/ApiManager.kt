package com.giorgosyrr.vivaexample.data.remote



import com.giorgosyrr.vivaexample.data.model.DataFromApi
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor(private val apiService: ApiService) {
    val listBoardData: Observable<List<DataFromApi>>
        get() = apiService.listBoardData

}