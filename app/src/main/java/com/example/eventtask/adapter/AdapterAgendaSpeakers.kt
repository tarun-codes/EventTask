package com.example.eventtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventtask.R
import com.example.eventtask.model.AgendaSpeakers

class AdapterAgendaSpeakers(private val speakersList: List<AgendaSpeakers>) :
    RecyclerView.Adapter<AdapterAgendaSpeakers.AgendaSpeakerViewHolder>() {


    class AgendaSpeakerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtSpeakerName: TextView = itemView.findViewById(R.id.txt_speaker_name)
        val txtSpeakerTitle: TextView = itemView.findViewById(R.id.txt_speaker_title)
        val imgSpeaker: ImageView = itemView.findViewById(R.id.img_speaker)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaSpeakerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_speakers_list, parent, false)
        return AgendaSpeakerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgendaSpeakerViewHolder, position: Int) {
        val agendaSpeaker = speakersList[position]

        Glide.with(holder.imgSpeaker)
            .load(agendaSpeaker.image)
            .into(holder.imgSpeaker)

        holder.txtSpeakerName.text = agendaSpeaker.name
        holder.txtSpeakerTitle.text = agendaSpeaker.title
    }

    override fun getItemCount(): Int {
        return speakersList.size
    }
}