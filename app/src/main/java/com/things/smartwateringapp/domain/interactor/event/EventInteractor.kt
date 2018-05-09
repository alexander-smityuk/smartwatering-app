package com.things.smartwateringapp.domain.interactor.event

import com.things.smartwateringapp.data.repository.event.EventRepository
import com.things.smartwateringapp.domain.entity.Event
import io.reactivex.Single
import javax.inject.Inject


class EventInteractor @Inject constructor(private val repository: EventRepository) {

    fun putEvent(event: Event){
        repository.putEvent(event)
    }

    fun getEvents() : Single<List<Event>> {
        return repository.getEvents()
    }
}