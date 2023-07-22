package com.example.eventtask.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventtask.R
import com.example.eventtask.R.color.white
import com.example.eventtask.model.Data
import com.example.eventtask.Utils
import com.google.android.material.card.MaterialCardView

class AdapterEvents(private val dataList: List<Data>, val ctx: Context, val clickListener: EventItemClickListener) : RecyclerView.Adapter<AdapterEvents.ViewHolder>() {

    interface EventItemClickListener {
        fun onItemClick(data: Data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_events_list, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = dataList[position]


        holder.txtEventName.text = dataItem.name
        //holder.cardLayout.setCardBackgroundColor(white)
        holder.cardLayout.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.white))
        holder.cardLayout.setStrokeColor(ContextCompat.getColor(ctx, R.color.stroke_color))
        holder.cardLayout.strokeWidth = 1


        val utils = Utils()

        val startTime = dataItem.startDate?.let { utils.getFormattedTime(it) }
        val endTime = dataItem.endDate?.let { utils.getFormattedTime(it) }

        val eventTime = "$startTime - $endTime EST"
        holder.txtEventTime.text = eventTime


        val attendeesAdapter = AdapterAttendeesList(dataItem.attendees, 7)
        holder.recyclerViewAttendees.layoutManager =
            LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerViewAttendees.adapter = attendeesAdapter

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtEventName: TextView = itemView.findViewById(R.id.txt_event_name)
        val txtEventTime: TextView = itemView.findViewById(R.id.txt_event_time)
        val cardLayout: MaterialCardView = itemView.findViewById(R.id.card_layout)
        val recyclerViewAttendees: RecyclerView = itemView.findViewById(R.id.recycler_view_attendees)


        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(dataList[position])
                }
            }
        }
    }




}
