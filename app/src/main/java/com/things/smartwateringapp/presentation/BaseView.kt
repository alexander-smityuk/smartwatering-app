package com.things.smartwateringapp.presentation

import android.support.annotation.StringRes

interface BaseView {
    fun showProgress()

    fun hideProgress()

    fun showError(@StringRes message : Int)
}