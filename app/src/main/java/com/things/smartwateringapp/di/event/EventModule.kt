package com.things.smartwateringapp.di.event

import com.google.firebase.database.FirebaseDatabase
import com.things.smartwateringapp.data.repository.event.EventRepository
import com.things.smartwateringapp.domain.interactor.event.EventInteractor
import com.things.smartwateringapp.presentation.event.EventPresenter
import dagger.Module
import dagger.Provides

@Module
class EventModule {

    @Provides
    @EventScope
    fun provideEventRepository(database: FirebaseDatabase): EventRepository {
        return EventRepository(database)
    }

    @Provides
    @EventScope
    fun provideEventInteractor(repository: EventRepository): EventInteractor {
        return EventInteractor(repository)
    }

    @Provides
    @EventScope
    fun provideEventPresenter(interactor: EventInteractor): EventPresenter {
        return EventPresenter(interactor)
    }
}