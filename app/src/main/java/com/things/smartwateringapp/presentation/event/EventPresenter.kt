package com.things.smartwateringapp.presentation.event

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.things.smartwateringapp.R
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.domain.interactor.event.EventInteractor
import com.things.smartwateringapp.presentation.BasePresenter
import java.util.*
import javax.inject.Inject


class EventPresenter @Inject constructor(private val interactor: EventInteractor) : BasePresenter<EventView>(){

    private var view: EventView? = null

    override fun bindView(view: EventView) {
        this.view = view
    }

    override fun unbindView() {
        this.view = null
        dispose()
    }

    fun getEvents(){
        view?.let{
            it.showProgress()
            interactor.getEvents()
                    .subscribe(this::handleSuccessGetEvents, this::handleErrorGetEvents)
                    .connect()
        }
    }

    fun getEventsByDay(date: CalendarDay){
        view?.let{
            interactor.getEventsByDate(date.calendar)
                    .subscribe(this::handleSuccessGetEventsByDate, this::handleErrorGetEventsByDate)
                    .connect()
        }
    }

    fun saveEvent(event: Event){
        interactor.putEvent(event)
    }

    private fun handleSuccessGetEvents(events: List<CalendarDay>) {
        view?.let{
            it.hideProgress()
            it.showCalendarEvents(events)
        }
    }

    private fun handleErrorGetEvents(throwable: Throwable) {
        view?.let{
            it.hideProgress()
            it.showError(R.string.error)
        }
    }

    private fun handleSuccessGetEventsByDate(events: List<Event>) {
        view?.let{
            it.hideProgress()
            it.showEventsByDate(events)
        }
    }

    private fun handleErrorGetEventsByDate(throwable: Throwable) {
        view?.let{
            it.hideProgress()
            it.showError(R.string.error)
        }
    }
}