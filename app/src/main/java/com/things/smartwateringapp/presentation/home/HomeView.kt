package com.things.smartwateringapp.presentation.home

import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.domain.entity.Status
import com.things.smartwateringapp.presentation.BaseView

interface HomeView : BaseView {
    fun showInfo(info: DataInfo)

    fun showStatusInfo(status: Status)

    fun showPlantType(type: Int)

    fun showNearestEvent(event: Event)

    fun hideNearestEvent()
}