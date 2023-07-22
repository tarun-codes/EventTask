package com.example.eventtask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventtask.adapter.AdapterEvents
import com.example.eventtask.adapter.DateAdapter
import com.example.eventtask.model.AgendaResponse
import com.example.eventtask.model.EventData
import com.example.eventtask.model.Events
import java.text.SimpleDateFormat
import java.util.Locale

class EventsActivity : AppCompatActivity(), AdapterEvents.EventItemClickListener,
    DateAdapter.DateClickListener {

    private val viewModel: EventsViewmodel by viewModels()
    private lateinit var events: Events
    private lateinit var agenda: AgendaResponse
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        recyclerView = findViewById(R.id.recycler_view_events)
        viewModel.responseData.observe(this, Observer { response ->

            response?.let {
                events = response
                Log.d("response_json", events.replyMsg.toString())


                val imgProfile: ImageView = findViewById(R.id.img_profile)
                Glide.with(imgProfile)
                    .load(events.data[0].attendees[0].image)
                    .into(imgProfile)

                val startDateList = mutableListOf<String>()

                val inputSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                val outputSdf = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())

                for (event in events.data) {
                    val date = inputSdf.parse(event.startDate)
                    val formattedDate = outputSdf.format(date)
                    if (!startDateList.contains(formattedDate))
                        startDateList.add(formattedDate)
                }

                Log.d("startDateList", startDateList.toString())

                val recyclerViewDates: RecyclerView = findViewById(R.id.recycler_view_dates)
                val dateAdapter = DateAdapter(this, startDateList, this)

                recyclerViewDates.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewDates.adapter = dateAdapter


            }
        })

        viewModel.agendaResponseData.observe(this, Observer { response ->
            response?.let {
                agenda = response
                Log.d("response_json_new", agenda.replyMsg.toString())
                val intent = Intent(this, AgendaDetailsActivity::class.java)
                intent.putExtra("agenda", agenda)
                startActivity(intent)

            }
        })


        viewModel.getEvents()


    }

    override fun onItemClick(eventData: EventData) {
        Log.d("item_clicked", eventData.id.toString())
        viewModel.getAgendaDetails(eventData.id.toString())


    }

    override fun onDateClick(selectedDate: String) {

        val inputSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputSdf = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())

        val eventDataList = mutableListOf<EventData>()

        for (event in events.data) {
            val date = inputSdf.parse(event.startDate)
            val formattedDate = outputSdf.format(date)
            if (selectedDate.equals(formattedDate))
                eventDataList.add(event)
        }

        Log.d("item_clicked", selectedDate.toString())

        val adapter = AdapterEvents(eventDataList, baseContext, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }


}