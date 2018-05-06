package com.things.smartwateringapp.presentation.home

import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.presentation.BaseView

interface HomeView : BaseView {
    fun showInfo(info: DataInfo)
}