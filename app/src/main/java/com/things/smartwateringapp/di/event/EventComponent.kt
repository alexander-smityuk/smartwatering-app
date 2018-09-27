package com.things.smartwateringapp.di.event

import com.things.smartwateringapp.ui.event.EventFragment
import dagger.Subcomponent

@Subcomponent(modules = [EventModule::class])
@EventScope
interface EventComponent {

    @Subcomponent.Builder
    interface Builder {
        fun eventModule(eventModule: EventModule): EventComponent.Builder
        fun build(): EventComponent
    }

    fun inject(fragment: EventFragment)
}