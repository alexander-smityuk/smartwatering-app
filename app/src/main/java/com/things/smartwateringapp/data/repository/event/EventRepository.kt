package com.things.smartwateringapp.data.repository.event

import com.google.firebase.database.FirebaseDatabase
import com.things.smartwateringapp.domain.entity.Event
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventRepository @Inject constructor(private val database: FirebaseDatabase) {

    fun putEvent(event: Event){
        database.getReference("events").push().setValue(event)
    }

    fun getEvents() : Single<List<Event>> {
        return RxFirebaseDatabase.observeSingleValueEvent(database.getReference("events"), DataSnapshotMapper.listOf(Event::class.java))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toSingle()
    }
}