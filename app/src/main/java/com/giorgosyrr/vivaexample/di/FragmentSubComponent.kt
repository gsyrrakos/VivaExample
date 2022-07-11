package com.giorgosyrr.vivaexample.di



import com.giorgosyrr.vivaexample.basicfunctionfragment.ListBoardFragment
import com.giorgosyrr.vivaexample.di.custom_annotation.PerFragment

import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentSubComponent {
    fun inject(listBoardFragment: ListBoardFragment?)
}