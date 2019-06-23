package com.mgalperina.swoosh

import android.app.Application

open class SwooshApplication : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = createMyComponent()
    }

    protected open fun createMyComponent(): AppComponent{
        return DaggerAppComponent
            .builder()
            .appModule(AppModule())
            .build()
    }

}

