package com.giorgosyrr.vivaexample.data.remote


import com.giorgosyrr.vivaexample.Constants
import com.giorgosyrr.vivaexample.data.model.DataFromApi
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiService {


    @get:GET(Constants.API_RATES_ENDPOINT)

    val listBoardData: Observable<List<DataFromApi>>


}