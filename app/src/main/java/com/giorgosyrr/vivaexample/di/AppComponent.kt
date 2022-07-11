package com.giorgosyrr.vivaexample.di




import com.giorgosyrr.vivaexample.BaseApplication
import com.giorgosyrr.vivaexample.data.remote.DataBaseModule

import com.giorgosyrr.vivaexample.data.remote.RemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RemoteModule::class, DataBaseModule::class])
interface AppComponent {
    fun inject(baseApplication: BaseApplication?)
    fun converterActivitySubcomponent(fragmentModule: FragmentModule?): FragmentSubComponent?
}