package com.things.smartwateringapp.ui

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    protected abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
    }

    protected fun addFragment(@IdRes containerViewId: Int, fragment: BaseFragment) {
        supportFragmentManager
                .beginTransaction()
                .add(containerViewId, fragment)
                .commit()
    }

    protected fun replaceFragment(@IdRes containerViewId: Int, fragment: BaseFragment) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack(fragment.javaClass.simpleName)

        if (!supportFragmentManager.isStateSaved) {
            transaction.commit()
        }
    }
}