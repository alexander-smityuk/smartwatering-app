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
