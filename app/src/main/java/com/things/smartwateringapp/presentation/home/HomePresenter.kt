package com.things.smartwateringapp.presentation.home

import com.things.smartwateringapp.R
import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.domain.entity.Status
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

    fun getDataInfo(forceUpdate: Boolean) {
        view?.let {
            if (!forceUpdate) it.showProgress()
            interactor.getDataInfo()
                    .subscribe(this::handleSuccessGetDataInfo, this::handleErrorGetDataInfo)
                    .connect()
        }
    }

    fun getStatusInfo() {
        view?.let {
            interactor.getStatusInfo()
                    .subscribe(this::handleSuccessGetStatusInfo, this::handleErrorGetStatusInfo)
                    .connect()
        }
    }

    fun putWaterStatus(waterStatus: Boolean) {
        interactor.putWaterStatus(waterStatus)
    }

    fun putAutoStatus(autoStatus: Boolean) {
        interactor.putAutoStatus(autoStatus)
    }

    private fun handleSuccessGetDataInfo(dataInfo: DataInfo) {
        view?.let {
            it.hideProgress()
            it.showInfo(dataInfo)
        }
    }

    private fun handleErrorGetDataInfo(throwable: Throwable) {
        view?.let {
            it.hideProgress()
            it.showError(R.string.error)
        }
    }

    private fun handleSuccessGetStatusInfo(status: Status) {
        view?.let {
            it.hideProgress()
            it.showStatusInfo(status)
        }
    }

    private fun handleErrorGetStatusInfo(throwable: Throwable) {
        view?.let {
            it.hideProgress()
            it.showError(R.string.error)
        }
    }
}