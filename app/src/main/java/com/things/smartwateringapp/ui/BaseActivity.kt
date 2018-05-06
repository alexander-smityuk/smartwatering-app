package com.things.smartwateringapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(layoutResId)
    }

    protected fun addFragment(@IdRes containerViewId: Int, fragment: BaseFragment) {
        supportFragmentManager
                .beginTransaction()
                .add(containerViewId, fragment)
                .commit()
    }
}