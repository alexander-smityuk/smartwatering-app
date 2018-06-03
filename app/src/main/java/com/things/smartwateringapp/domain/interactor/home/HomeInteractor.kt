package com.things.smartwateringapp.domain.interactor.home

import com.things.smartwateringapp.data.repository.event.EventRepository
import com.things.smartwateringapp.data.repository.info.InfoRepository
import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.domain.entity.Status
import com.things.smartwateringapp.domain.entity.Type
import io.reactivex.Single
import javax.inject.Inject


class HomeInteractor @Inject constructor(private val infoRepository: InfoRepository, private val eventRepository: EventRepository) {

    fun getDataInfo() : Single<DataInfo> {
        return infoRepository.getDataInfo()
    }

    fun getStatusInfo() : Single<Status>{
        return infoRepository.getStatusInfo()
    }

    fun putWaterStatus(waterStatus: Boolean){
        infoRepository.putWaterStatus(waterStatus)
    }

    fun putAutoStatus(autoStatus: Boolean){
        infoRepository.putAutoStatus(autoStatus)
    }

    fun putPlantType(type: Int){
        infoRepository.putPlantType(type)
    }

    fun getPlantType() : Single<Type>{
        return infoRepository.getPlantType()
    }

    fun getNearestEvent() : Single<Event> {
        return eventRepository.getNearestEvent()
    }
}