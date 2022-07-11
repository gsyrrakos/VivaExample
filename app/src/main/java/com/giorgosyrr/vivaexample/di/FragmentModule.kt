package com.giorgosyrr.vivaexample.di


import com.giorgosyrr.vivaexample.basicfunctionfragment.ListBoardContract
import com.giorgosyrr.vivaexample.basicfunctionfragment.ListBoardFragment
import com.giorgosyrr.vivaexample.basicfunctionfragment.ListBoardPresenter
import com.giorgosyrr.vivaexample.data.repository.DataRepository
import com.giorgosyrr.vivaexample.data.repository.DataRepositoryImpl
import com.giorgosyrr.vivaexample.di.custom_annotation.PerFragment

import dagger.Module
import dagger.Provides

@Module
class FragmentModule(converterActivity: ListBoardFragment) {
    private val listBoardFragment: ListBoardFragment = converterActivity

    @Provides
    @PerFragment
    fun provideView(): ListBoardContract.View {
        return listBoardFragment
    }

    @Provides
    @PerFragment
    fun providePresenter(presenter: ListBoardPresenter?): ListBoardContract.Presenter? {
        return presenter
    }
    @Provides
    @PerFragment
    fun provideLoginInteractor(dataManager: DataRepositoryImpl): DataRepository {
        return dataManager
    }

}