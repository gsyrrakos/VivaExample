package com.giorgosyrr.vivaexample.basicfunctionfragment

import com.giorgosyrr.vivaexample.data.model.DataFromApi


interface ListBoardContract {
    interface View {
        fun loadFirstData(listBoard: List<DataFromApi>)
        fun displayNetworkError(text: String?)
    }

    interface Presenter {
        fun getData(hasNetwork: Boolean)
        fun deleteAllFromDb()
        fun onDestroy()
    }
}