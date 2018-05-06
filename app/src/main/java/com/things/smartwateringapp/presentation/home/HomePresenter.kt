package com.things.smartwateringapp.presentation.home

import com.things.smartwateringapp.R
import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.domain.interactor.home.HomeInteractor
import com.things.smartwateringapp.presentation.BasePresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(private val interactor: HomeInteractor) : BasePresenter<HomeView>() {

    private var view: HomeView? = null

    override fun bindView(view: HomeView) {
        this.view = view
    }

    override fun unbindView() {
        this.view = null
        dispose()
    }

    fun getDataInfo() {
        view?.let {
            interactor.getDataInfo()
                    .subscribe(this::handleSuccessGetDataInfo, this::handleErrorGetDataInfo)
                    .connect()
        }
    }

    private fun handleSuccessGetDataInfo(dataInfo: DataInfo) {
        view?.showInfo(dataInfo)
    }

    private fun handleErrorGetDataInfo(throwable: Throwable) {
        view?.showError(R.string.error)
    }
}