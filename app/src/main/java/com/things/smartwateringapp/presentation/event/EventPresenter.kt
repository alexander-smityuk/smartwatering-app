package com.things.smartwateringapp.presentation.event

import com.things.smartwateringapp.R
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.domain.interactor.event.EventInteractor
import com.things.smartwateringapp.presentation.BasePresenter
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

            interactor.getEvents()
                    .subscribe(this::handleSuccessGetEvents, this::handleErrorGetEvents)
                    .connect()
        }
    }

    fun saveEvent(event: Event){
        interactor.putEvent(event)
    }

    private fun handleSuccessGetEvents(events: List<Event>) {
        view?.showEvents(events)
    }

    private fun handleErrorGetEvents(throwable: Throwable) {
        view?.showError(R.string.error)
    }
}