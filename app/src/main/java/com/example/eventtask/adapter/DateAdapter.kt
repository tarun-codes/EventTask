package com.example.eventtask.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eventtask.R

class DateAdapter(
    private val ctx: Context,
    private val dates: List<String>,
    private val onDateClickListener: DateClickListener

) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    interface DateClickListener {
        fun onDateClick(selectedDate: String)
    }

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtViewDate: TextView = itemView.findViewById(R.id.txt_view_date)

        init {
            txtViewDate.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    setSelectedItem(position)
                    onDateClickListener.onDateClick(dates[position])
                }
            }
        }

        fun bind(date: String) {
            txtViewDate.text = date
            if (adapterPosition == selectedPosition) {
                val selectedColor = ContextCompat.getColor(ctx, R.color.blue)
                txtViewDate.setTextColor(selectedColor)
            } else {
                val defaultColor = ContextCompat.getColor(ctx, R.color.text_heading_color)
                txtViewDate.setTextColor(defaultColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_date_list, parent, false)
        return DateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(dates[position])
    }

    override fun getItemCount() = dates.size

    private fun setSelectedItem(position: Int) {
        val previousSelected = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousSelected)
        notifyItemChanged(selectedPosition)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (dates.isNotEmpty()) {
            setSelectedItem(0)
            onDateClickListener.onDateClick(dates[0])
        }
    }
}
