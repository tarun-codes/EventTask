package com.example.eventtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventtask.R
import com.example.eventtask.model.Attendees

class AdapterAttendeesList(
    private val attendees: List<Attendees>,
    private val maxVisibleCount: Int
) :
    RecyclerView.Adapter<AdapterAttendeesList.AttendeesViewHolder>() {

    class AttendeesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewRemainingCount: TextView = itemView.findViewById(R.id.textViewRemainingCount)
        val imgAttendees: ImageView = itemView.findViewById(R.id.img_attendees)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendeesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_attendees_list, parent, false)
        return AttendeesViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttendeesViewHolder, position: Int) {
        val attendee = attendees[position]

        Glide.with(holder.imgAttendees)
            .load(attendee.image)
            .into(holder.imgAttendees)


        if (position == maxVisibleCount - 1 && attendees.size > maxVisibleCount) {
            val remainingCount = attendees.size - maxVisibleCount + 1
            holder.textViewRemainingCount.visibility = View.VISIBLE
            holder.textViewRemainingCount.text = "+$remainingCount"
        } else {
            holder.textViewRemainingCount.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return if (attendees.size > maxVisibleCount) maxVisibleCount else attendees.size
    }
}