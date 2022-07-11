package com.giorgosyrr.vivaexample.basicfunctionfragment


import android.util.Log
import com.giorgosyrr.vivaexample.data.model.DataFromApi
import com.giorgosyrr.vivaexample.data.repository.DataRepository
import com.giorgosyrr.vivaexample.di.custom_annotation.PerFragment
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@PerFragment
class ListBoardPresenter @Inject constructor(
    repository: DataRepository,
    private val view: ListBoardContract.View
) : ListBoardContract.Presenter {

    private var repository: DataRepository = repository
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getData(hasNetwork: Boolean) {
        if (hasNetwork) {
           // getDataRemote()
            selectAllFromDb()
        }
    }


    override fun deleteAllFromDb(){
        repository.deleteAllUsers()
    }

    private fun selectAllFromDb(){
       repository.databaseSelect
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableMaybeObserver<List<DataFromApi>?>() {
                override fun onSuccess(employee: List<DataFromApi>) {
                    if(employee.isNotEmpty()){
                        view.loadFirstData(employee)
                    }else{
                        getDataRemote()
                    }

                }

                override fun onError(e: Throwable) {
                    view.displayNetworkError(e.toString())
                }

                override fun onComplete() {
                    TODO("Not yet implemented")
                }
            })

    }

    private fun getDataRemote() {

        compositeDisposable.add(repository.listBoardFirstData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listBoard ->
                Log.d("edw",listBoard.size.toString())
                repository.insertData(listBoard)

                view.loadFirstData(listBoard) }
            ) { throwable -> view.displayNetworkError(throwable.toString())
            Log.d("edw",throwable.toString())})

    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}