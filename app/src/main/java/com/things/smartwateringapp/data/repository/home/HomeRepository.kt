package com.things.smartwateringapp.data.repository.home

import com.google.firebase.database.FirebaseDatabase
import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.domain.entity.Status
import com.things.smartwateringapp.domain.entity.Type
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class HomeRepository @Inject constructor(private val database: FirebaseDatabase) {

    fun getDataInfo(): Single<DataInfo> {
        return RxFirebaseDatabase.observeSingleValueEvent(database.getReference("data"), DataInfo::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
    }

    fun putWaterStatus(waterStatus: Boolean) {
        database.getReference("status").child("waterStatus").setValue(waterStatus)
    }

    fun putAutoStatus(autoStatus: Boolean) {
        database.getReference("status").child("autoStatus").setValue(autoStatus)
    }

    fun getStatusInfo(): Single<Status> {
        return RxFirebaseDatabase.observeSingleValueEvent(database.getReference("status"), Status::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
    }

    fun putPlantType(type: Int) {
        database.getReference("type").child("plantType").setValue(type)
    }

    fun getPlantType(): Single<Type> {
        return RxFirebaseDatabase.observeSingleValueEvent(database.getReference("type"), Type::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
    }

    fun getNearestEvent(): Single<Event> {
        return RxFirebaseDatabase.observeSingleValueEvent(database.getReference("events"), DataSnapshotMapper.listOf(Event::class.java))
                .toObservable()
                .flatMapIterable { it }
                .filter { it.dateTime > System.currentTimeMillis() }
                .sorted { event1, event2 -> (event1.dateTime - event2.dateTime).toInt() }
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}