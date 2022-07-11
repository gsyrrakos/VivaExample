package com.giorgosyrr.vivaexample


import android.app.Application
import com.giorgosyrr.vivaexample.data.remote.DataBaseModule
import com.giorgosyrr.vivaexample.data.remote.RemoteModule
import com.giorgosyrr.vivaexample.di.AppComponent
import com.giorgosyrr.vivaexample.di.AppModule
import com.giorgosyrr.vivaexample.di.DaggerAppComponent


class BaseApplication : Application() {
    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .remoteModule(RemoteModule())
            .dataBaseModule(DataBaseModule())
            .build()
        appComponent!!.inject(this)
    }
}