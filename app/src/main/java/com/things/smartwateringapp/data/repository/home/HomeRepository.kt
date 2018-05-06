package com.things.smartwateringapp.data.repository.home

import com.google.firebase.database.FirebaseDatabase
import com.things.smartwateringapp.domain.entity.DataInfo
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class HomeRepository @Inject constructor(private val database: FirebaseDatabase) {

    fun getDataInfo() : Single<DataInfo> {
       return RxFirebaseDatabase.observeSingleValueEvent(database.getReference("data"), DataInfo::class.java)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .toSingle()
    }
}