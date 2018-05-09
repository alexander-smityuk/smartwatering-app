package com.things.smartwateringapp.ui.event

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.things.smartwateringapp.App
import com.things.smartwateringapp.R
import com.things.smartwateringapp.di.event.EventModule
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.presentation.event.EventPresenter
import com.things.smartwateringapp.presentation.event.EventView
import com.things.smartwateringapp.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_event.*
import javax.inject.Inject

class EventFragment : BaseFragment(), EventView {

    override val layoutRes: Int = R.layout.fragment_event

    @Inject
    lateinit var presenter: EventPresenter

    private val adapter : EventListAdapter = EventListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent
                .eventComponentBuilder()
                .eventModule(EventModule())
                .build()
                .inject(this@EventFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)
        presenter.getEvents()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        fab.setOnClickListener({
            presenter.saveEvent(Event("Полив цветка", System.currentTimeMillis(), false))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindView()
    }

    override fun showEvents(events: List<Event>) {
        adapter.bind(events)
    }

    override fun showError(message: Int) {
        showSnackMessage(getString(message))
    }
}