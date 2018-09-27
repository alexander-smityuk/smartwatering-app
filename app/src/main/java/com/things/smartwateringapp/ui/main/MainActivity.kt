package com.things.smartwateringapp.ui.main

import android.os.Bundle
import com.things.smartwateringapp.R
import com.things.smartwateringapp.ui.BaseActivity
import com.things.smartwateringapp.ui.event.EventFragment
import com.things.smartwateringapp.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutResId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFragment(R.id.fragmentContainer, HomeFragment())

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> replaceFragment(R.id.fragmentContainer, HomeFragment())
                R.id.nav_timetable -> replaceFragment(R.id.fragmentContainer, EventFragment())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

}
