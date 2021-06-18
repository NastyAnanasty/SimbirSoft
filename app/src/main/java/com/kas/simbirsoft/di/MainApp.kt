package com.kas.simbirsoft.di

import android.app.Application
import com.kas.simbirsoft.di.component.AppComponent
import com.kas.simbirsoft.di.component.DaggerAppComponent
import com.kas.simbirsoft.di.module.DataModule

class MainApp: Application() {
    private val _appComponent by lazy {
        DaggerAppComponent.builder().dataModule(DataModule(this)).build()
    }

    val appComponent: AppComponent get() = _appComponent
}