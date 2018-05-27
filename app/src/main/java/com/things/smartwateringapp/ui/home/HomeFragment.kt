package com.things.smartwateringapp.ui.home

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.hookedonplay.decoviewlib.charts.SeriesItem
import com.hookedonplay.decoviewlib.events.DecoEvent
import com.things.smartwateringapp.App
import com.things.smartwateringapp.R
import com.things.smartwateringapp.di.home.HomeModule
import com.things.smartwateringapp.domain.entity.DataInfo
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.domain.entity.Status
import com.things.smartwateringapp.presentation.home.HomePresenter
import com.things.smartwateringapp.presentation.home.HomeView
import com.things.smartwateringapp.ui.BaseFragment
import com.things.smartwateringapp.utils.toDateString
import kotlinx.android.synthetic.main.event_item.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject


class HomeFragment : BaseFragment(), HomeView {

    override val layoutRes: Int = R.layout.fragment_home

    @Inject
    lateinit var presenter: HomePresenter

    companion object {
        const val MAIN_TRACK_DURATION = 3000L
        const val BACK_TRACK_DURATION = 500L
    }

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
        presenter.getDataInfo(false)
        presenter.getStatusInfo()
        presenter.getPlantType()
        presenter.getNearestEvent()

        val adapter = ArrayAdapter.createFromResource(context, R.array.types, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        swiperefresh.setOnRefreshListener {
            presenter.getDataInfo(true)
            presenter.getStatusInfo()
        }

        manualWaterSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.putWaterStatus(isChecked)
        }

        autoWaterSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.putAutoStatus(isChecked)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.putPlantType(position + 1)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbindView()
        humidityChart.executeReset()
        soilMoistureChart.executeReset()
    }

    override fun showInfo(info: DataInfo) {
        swiperefresh.isRefreshing = false

        humidityText.text = String.format(Locale.getDefault(), resources.getString(R.string.humidity_percentage), info.humidity)
        soilMoistureText.text = String.format(Locale.getDefault(), resources.getString(R.string.soil_moisture_percentage), info.soilMoisture)
        temperature.text = "${info.temperature}°C"

        setupHumidityChart(info.humidity.toFloat())
        setupSoilMoistureChart(info.soilMoisture.toFloat())
    }

    override fun showStatusInfo(status: Status) {
        manualWaterSwitch.isChecked = status.waterStatus
        autoWaterSwitch.isChecked = status.autoStatus
    }

    override fun showPlantType(type: Int) {
        spinner.setSelection(type - 1)
    }

    override fun showNearestEvent(eventData: Event) {
        event.visibility = View.VISIBLE
        newWaterTxt.visibility = View.VISIBLE

        if(eventData.isDone)
            status.text = "Статус : выполнен"
        else
            status.text = "Статус : ожидает"
        date.text = eventData.dateTime.toDateString()
    }

    override fun hideNearestEvent() {
        event.visibility = View.GONE
        newWaterTxt.visibility = View.GONE
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
        showSnackMessage(getString(R.string.error))
    }

    private fun setupHumidityChart(humidity: Float) {

        val backgroundTrack = SeriesItem.Builder(ContextCompat.getColor(context!!, R.color.materialGrey))
                .setRange(0f, 100f, 100f)
                .setInitialVisibility(false)
                .build()

        humidityChart.addEvent(DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDuration(BACK_TRACK_DURATION)
                .setIndex(humidityChart.addSeries(backgroundTrack)).build())

        val mainTrack = SeriesItem.Builder(ContextCompat.getColor(context!!, R.color.materialYellow))
                .setRange(0f, 100f, 0f)
                .setInterpolator(OvershootInterpolator())
                .setShowPointWhenEmpty(true)
                .setCapRounded(true)
                .setSpinDuration(MAIN_TRACK_DURATION)
                .setChartStyle(SeriesItem.ChartStyle.STYLE_DONUT)
                .build()

        humidityChart.addEvent(DecoEvent.Builder(humidity).setIndex(humidityChart.addSeries(mainTrack)).build())
    }

    private fun setupSoilMoistureChart(soilMoisture: Float) {

        val backgroundTrack = SeriesItem.Builder(ContextCompat.getColor(context!!, R.color.materialGrey))
                .setRange(0f, 100f, 100f)
                .setInitialVisibility(false)
                .build()

        soilMoistureChart.addEvent(DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDuration(BACK_TRACK_DURATION)
                .setIndex(soilMoistureChart.addSeries(backgroundTrack)).build())

        val series = SeriesItem.Builder(ContextCompat.getColor(context!!, R.color.materialBlue))
                .setRange(0f, 100f, 0f)
                .setInterpolator(OvershootInterpolator())
                .setShowPointWhenEmpty(true)
                .setCapRounded(true)
                .setSpinDuration(MAIN_TRACK_DURATION)
                .setChartStyle(SeriesItem.ChartStyle.STYLE_DONUT)
                .build()

        soilMoistureChart.addEvent(DecoEvent.Builder(soilMoisture).setIndex(soilMoistureChart.addSeries(series)).build())

        series.addArcSeriesItemListener(object : SeriesItem.SeriesItemListener {
            override fun onSeriesItemAnimationProgress(percentComplete: Float, currentPosition: Float) {
                val percentFilled = (currentPosition - series.minValue) / (series.maxValue - series.minValue)
                soilMoisturePercentage.text = String.format("%.0f%%", percentFilled * 100f)
            }

            override fun onSeriesItemDisplayProgress(percentComplete: Float) {
            }
        })
    }
}