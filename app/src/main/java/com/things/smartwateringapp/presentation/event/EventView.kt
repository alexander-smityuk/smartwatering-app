package com.things.smartwateringapp.presentation.event

import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.presentation.BaseView


interface EventView : BaseView {
    fun showEvents(events: List<Event>)
}