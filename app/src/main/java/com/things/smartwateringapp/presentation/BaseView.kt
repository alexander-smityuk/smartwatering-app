package com.things.smartwateringapp.presentation

import android.support.annotation.StringRes

interface BaseView {
    fun showError(@StringRes message : Int)
}