package com.things.smartwateringapp.di.application

import com.things.smartwateringapp.di.event.EventComponent
import com.things.smartwateringapp.di.home.HomeComponent
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AndroidModule::class, DataModule::class])
@Singleton
interface AppComponent {
    fun homeComponentBuilder(): HomeComponent.Builder

    fun eventComponentBuilder(): EventComponent.Builder
}