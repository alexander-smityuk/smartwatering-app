package com.things.smartwateringapp.ui.event

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.things.smartwateringapp.R
import com.things.smartwateringapp.domain.entity.Event
import com.things.smartwateringapp.utils.inflate
import com.things.smartwateringapp.utils.toDateString
import kotlinx.android.synthetic.main.event_item.view.*

class EventListAdapter : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    private val events = mutableListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.event_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size

    fun bind(eventList: List<Event>) {
        events.clear()
        events.addAll(eventList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Event)= with(itemView) {
            name.text = item.name
            if(item.isDone)
                status.text = "Статус : выполнен"
            else
                status.text = "Статус : ожидает"
            date.text = item.dateTime.toDateString()
        }
    }
}