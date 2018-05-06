package com.things.smartwateringapp.di.home

import com.google.firebase.database.FirebaseDatabase
import com.things.smartwateringapp.data.repository.home.HomeRepository
import com.things.smartwateringapp.domain.interactor.home.HomeInteractor
import com.things.smartwateringapp.presentation.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @HomeScope
    fun provideHomeRepository(database: FirebaseDatabase): HomeRepository {
        return HomeRepository(database)
    }

    @Provides
    @HomeScope
    fun provideHomeInteractor(repository: HomeRepository): HomeInteractor {
        return HomeInteractor(repository)
    }

    @Provides
    @HomeScope
    fun provideHomePresenter(interactor: HomeInteractor): HomePresenter {
        return HomePresenter(interactor)
    }

}