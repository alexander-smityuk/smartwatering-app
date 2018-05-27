package com.things.smartwateringapp.domain.interactor.home

import com.things.smartwateringapp.data.repository.home.HomeRepository
import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.domain.entity.Status
import com.things.smartwateringapp.domain.entity.Type
import io.reactivex.Single
import javax.inject.Inject


class HomeInteractor @Inject constructor(private val repository: HomeRepository) {

    fun getDataInfo() : Single<DataInfo> {
        return repository.getDataInfo()
    }

    fun getStatusInfo() : Single<Status>{
        return repository.getStatusInfo()
    }

    fun putWaterStatus(waterStatus: Boolean){
        repository.putWaterStatus(waterStatus)
    }

    fun putAutoStatus(autoStatus: Boolean){
        repository.putAutoStatus(autoStatus)
    }

    fun putPlantType(type: Int){
        repository.putPlantType(type)
    }

    fun getPlantType() : Single<Type>{
        return repository.getPlantType()
    }

    fun getNearestEvent() : Single<Event> {
        return repository.getNearestEvent()
    }
}