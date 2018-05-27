package com.things.smartwateringapp.ui.event

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.things.smartwateringapp.App
import com.things.smartwateringapp.R
import com.things.smartwateringapp.di.event.EventModule
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.presentation.event.EventPresenter
import com.things.smartwateringapp.presentation.event.EventView
import com.things.smartwateringapp.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_event.*
import java.util.*
import javax.inject.Inject


class EventFragment : BaseFragment(), EventView {

    override val layoutRes: Int = R.layout.fragment_event

    @Inject
    lateinit var presenter: EventPresenter

    private val adapter: EventListAdapter = EventListAdapter()
    private val dateAndTime = Calendar.getInstance()

    private lateinit var rootView : View

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
        presenter.getEventsByDay(CalendarDay.from(dateAndTime))

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        calendarView.selectedDate = CalendarDay.from(dateAndTime)
        calendarView.setOnDateChangedListener { widget, date, selected -> presenter.getEventsByDay(date) }

        fab.setOnClickListener({
            rootView = activity!!.layoutInflater.inflate(R.layout.dialog_create_event, null)
            val dialog = AlertDialog.Builder(context!!).setTitle("Новый полив")
                    .setView(rootView)
                    .setPositiveButton("Создать", { dialog, id ->
                        presenter.saveEvent(Event(rootView.findViewById<EditText>(R.id.etTitle)?.text.toString(), dateAndTime.timeInMillis, false))
                        presenter.getEvents()
                    })
                    .setNegativeButton("Отмена", { dialog, id ->
                        dialog.cancel()
                    })
                    .create()

            rootView.findViewById<EditText>(R.id.etDate)?.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
                if(hasFocus) {
                    DatePickerDialog(context, dateListener,
                            dateAndTime.get(Calendar.YEAR),
                            dateAndTime.get(Calendar.MONTH),
                            dateAndTime.get(Calendar.DAY_OF_MONTH))
                            .show()
                }
            }

            rootView.findViewById<EditText>(R.id.etTime)?.setOnFocusChangeListener { view: View, hasFocus: Boolean ->
                if(hasFocus) {
                    TimePickerDialog(context, timeListener,
                            dateAndTime.get(Calendar.HOUR_OF_DAY),
                            dateAndTime.get(Calendar.MINUTE), true)
                            .show()
                }
            }

            dialog.show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindView()
    }

    override fun showEventsByDate(events: List<Event>) {
        adapter.bind(events)
    }

    override fun showCalendarEvents(events: List<CalendarDay>) {
        calendarView.addDecorator(EventDecorator(ContextCompat.getColor(context!!, R.color.colorPrimary), events))
    }

    override fun showProgress() {
        rootLayout.visibility = View.GONE
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        rootLayout.visibility = View.VISIBLE
        progress.visibility = View.GONE
    }

    override fun showError(message: Int) {
        showSnackMessage(getString(message))
    }

    var dateListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        dateAndTime.set(Calendar.YEAR, year)
        dateAndTime.set(Calendar.MONTH, monthOfYear)
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        rootView.findViewById<EditText>(R.id.etDate).setText("$dayOfMonth/${monthOfYear + 1}/$year", TextView.BufferType.EDITABLE)
    }

    var timeListener: TimePickerDialog.OnTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
        dateAndTime.set(Calendar.MINUTE, minute)
        rootView.findViewById<EditText>(R.id.etTime).setText("$hourOfDay:$minute", TextView.BufferType.EDITABLE)
    }
}