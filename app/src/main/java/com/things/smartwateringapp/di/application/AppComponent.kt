package com.things.smartwateringapp.di.application

import dagger.Component
import javax.inject.Singleton

@Component(modules = [AndroidModule::class])
@Singleton
interface AppComponent {

}