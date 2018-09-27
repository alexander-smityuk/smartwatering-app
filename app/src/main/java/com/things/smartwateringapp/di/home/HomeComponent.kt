package com.things.smartwateringapp.di.home

import com.things.smartwateringapp.ui.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
@HomeScope
interface HomeComponent {

    @Subcomponent.Builder
    interface Builder {
        fun homeModule(homeModule: HomeModule): HomeComponent.Builder
        fun build(): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}