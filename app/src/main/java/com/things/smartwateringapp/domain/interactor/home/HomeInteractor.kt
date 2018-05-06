package com.things.smartwateringapp.domain.interactor.home

import com.things.smartwateringapp.data.repository.home.HomeRepository
import com.things.smartwateringapp.domain.entity.DataInfo
import io.reactivex.Single
import javax.inject.Inject


class HomeInteractor @Inject constructor(private val repository: HomeRepository) {

    fun getDataInfo() : Single<DataInfo> {
        return repository.getDataInfo()
    }
}