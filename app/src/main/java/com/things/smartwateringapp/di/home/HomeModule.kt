package com.things.smartwateringapp.di.home

import com.google.firebase.database.FirebaseDatabase
import com.things.smartwateringapp.data.repository.event.EventRepository
import com.things.smartwateringapp.data.repository.info.InfoRepository
import com.things.smartwateringapp.domain.interactor.home.HomeInteractor
import com.things.smartwateringapp.presentation.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @HomeScope
    fun provideHomeRepository(database: FirebaseDatabase): InfoRepository {
        return InfoRepository(database)
    }

    @Provides
    @HomeScope
    fun provideHomeInteractor(repository: InfoRepository, eventRepository: EventRepository): HomeInteractor {
        return HomeInteractor(repository, eventRepository)
    }

    @Provides
    @HomeScope
    fun provideHomePresenter(interactor: HomeInteractor): HomePresenter {
        return HomePresenter(interactor)
    }

}