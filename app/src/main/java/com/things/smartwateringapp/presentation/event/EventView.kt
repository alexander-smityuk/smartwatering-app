package com.things.smartwateringapp.presentation.event

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.presentation.BaseView


interface EventView : BaseView {
    fun showCalendarEvents(events: List<CalendarDay>)

    fun showEventsByDate(events: List<Event>)
}