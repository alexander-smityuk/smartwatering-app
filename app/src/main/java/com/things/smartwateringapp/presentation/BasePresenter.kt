package com.things.smartwateringapp.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BasePresenter<in V : BaseView> {

    private val compositeDisposable = CompositeDisposable()

    abstract fun bindView(view: V)

    abstract fun unbindView()

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }

    protected fun dispose() {
        compositeDisposable.clear()
    }
}