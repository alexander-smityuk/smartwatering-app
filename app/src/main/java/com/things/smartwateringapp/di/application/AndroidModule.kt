package com.things.smartwateringapp.di.application

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AndroidModule(private val context: Context) {

    @Provides
    fun providesContext(): Context {
        return context
    }
}