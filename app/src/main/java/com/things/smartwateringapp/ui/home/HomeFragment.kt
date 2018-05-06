package com.things.smartwateringapp.ui.home

import android.os.Bundle
import android.view.View
import com.things.smartwateringapp.App
import com.things.smartwateringapp.R
import com.things.smartwateringapp.di.home.HomeModule
import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.presentation.home.HomePresenter
import com.things.smartwateringapp.presentation.home.HomeView
import com.things.smartwateringapp.ui.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment() , HomeView {

    override val layoutRes: Int = R.layout.fragment_home

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent
                .homeComponentBuilder()
                .homeModule(HomeModule())
                .build()
                .inject(this@HomeFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.bindView(this)
        presenter.getDataInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindView()
    }

    override fun showInfo(info: DataInfo) {

    }

    override fun showError(message: Int) {

    }
}