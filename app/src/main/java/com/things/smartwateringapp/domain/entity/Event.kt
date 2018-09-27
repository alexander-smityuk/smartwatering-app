package com.things.smartwateringapp.domain.entity

data class Event(val name: String = "",
                 val dateTime: Long = 0,
                 val isDone: Boolean = false)