package com.things.smartwateringapp.domain.interactor.event

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.things.smartwateringapp.data.repository.event.EventRepository
import com.things.smartwateringapp.domain.entity.Event
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class EventInteractor @Inject constructor(private val repository: EventRepository) {

    fun putEvent(event: Event){
        repository.putEvent(event)
    }

    fun getEvents() : Single<List<CalendarDay>> {
        return repository.getEvents()
                .toObservable()
                .flatMapIterable { it }
                .map{ it -> CalendarDay.from(Date(it.dateTime)) }
                .toList()
    }

    fun getEventsByDate(date : Calendar) : Single<List<Event>> {
        return repository.getEventsByDay(date)
    }
}