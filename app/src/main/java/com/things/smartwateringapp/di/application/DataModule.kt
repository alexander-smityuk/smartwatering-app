package com.things.smartwateringapp.di.application

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providesDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
}