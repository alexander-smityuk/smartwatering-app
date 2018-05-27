package com.things.smartwateringapp.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun Long.toDateString() : String {
    return SimpleDateFormat("dd MMMM, HH:mm", Locale.getDefault()).format(Date(this))
}

fun Calendar.isSameDay(date: Long): Boolean {
    val cal = Calendar.getInstance()
    cal.time = Date(date)

    return if (cal == null) false else cal[Calendar.ERA] == this[Calendar.ERA]
            && cal[Calendar.YEAR] == this[Calendar.YEAR]
            && cal[Calendar.DAY_OF_YEAR] == this[Calendar.DAY_OF_YEAR]
}
