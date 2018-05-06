package com.things.smartwateringapp

import android.app.Application
import com.things.smartwateringapp.di.application.AndroidModule
import com.things.smartwateringapp.di.application.AppComponent
import com.things.smartwateringapp.di.application.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .androidModule(AndroidModule(this))
                .build()
    }
}