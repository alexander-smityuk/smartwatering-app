package com.things.smartwateringapp.ui

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {

    abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(layoutRes, container, false)

    protected fun showSnackMessage(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
        view?.let {
            Snackbar.make(it, message, duration).show()
        }
    }

    protected fun replaceFragmentWithBackStack(@IdRes containerViewId: Int, fragment: BaseFragment) {
        activity?.let {
            val transaction = it.supportFragmentManager
                    .beginTransaction()
                    .replace(containerViewId, fragment)
                    .addToBackStack(fragment.javaClass.simpleName)


            if (!it.supportFragmentManager.isStateSaved) {
                transaction.commit()
            }
        }
    }
}