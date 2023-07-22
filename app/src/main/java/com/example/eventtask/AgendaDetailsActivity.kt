package com.example.eventtask

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventtask.adapter.AdapterAgendaSpeakers
import com.example.eventtask.adapter.AdapterAttendeesList
import com.example.eventtask.adapter.AdapterEvents
import com.example.eventtask.model.AgendaResponse
import org.jsoup.Jsoup

class AgendaDetailsActivity : AppCompatActivity() {
    private lateinit var agenda: AgendaResponse

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_details)
        agenda = (intent.getSerializableExtra("agenda") as? AgendaResponse)!!

        val imgHeader: ImageView = findViewById(R.id.img_agenda)
        val txtAgendaName: TextView = findViewById(R.id.txt_agenda_name)
        val recyclerViewAttendees: RecyclerView = findViewById(R.id.recycler_view_attendees)
        val recyclerViewSpeakers: RecyclerView = findViewById(R.id.recycler_view_speakers)
        val txtAgendaDate: TextView = findViewById(R.id.txt_agenda_date)
        val txtAgendaTime: TextView = findViewById(R.id.txt_agenda_time)
        val txtAgendaLocation: TextView = findViewById(R.id.txt_agenda_location)
        val btnRegistrationLinks: TextView = findViewById(R.id.btn_registration_links)
        val btnDocumentLink: TextView = findViewById(R.id.btn_document_links)
        val txtDescription: TextView = findViewById(R.id.txt_description)
        val txtSponsorName: TextView = findViewById(R.id.txt_sponsor_name)
        val imgSponsor: ImageView = findViewById(R.id.img_sponsor)
        val imgNavBack: ImageView = findViewById(R.id.img_nav_back)


        val headerImgPath = agenda.imgPath + agenda.data?.headerImg
        Glide.with(imgHeader)
            .load(headerImgPath)
            .into(imgHeader)

        txtAgendaName.text = agenda.data?.name

        val attendeesAdapter = agenda.data?.let { AdapterAttendeesList(it.attendees, 7) }
        recyclerViewAttendees.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewAttendees.adapter = attendeesAdapter

        val utils = Utils()

        val agendaDate = utils.getFormattedDate(agenda.data?.startDate.toString())


        val startTime = agenda.data?.startDate?.let { utils.getFormattedTime(it) }
        val endTime = agenda.data?.endDate?.let { utils.getFormattedTime(it) }

        val agendaTime = "$startTime - $endTime EST"



        txtAgendaDate.text = agendaDate
        txtAgendaTime.text = agendaTime

        txtAgendaLocation.text = agenda.data?.locationName

        val speakersAdapter =
            agenda.data?.let { AdapterAgendaSpeakers(agenda.data!!.agendaSpeakers) }
        recyclerViewSpeakers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewSpeakers.adapter = speakersAdapter


        btnRegistrationLinks.setOnClickListener {
            val url = agenda.data?.registerLinks?.get(0)?.registerLink
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        btnDocumentLink.setOnClickListener {
            val url = agenda.data?.agendaDocuments?.get(0)?.documentFile
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        val doc = agenda.data?.description?.let { Jsoup.parse(it) }
        if (doc != null) {
            txtDescription.text = doc.text()
        }

        txtSponsorName.text = agenda.data?.sponsorName


        if (agenda.data?.sponsorImg != null) {
            val sponsporImgPath = agenda.imgPath + agenda.data?.sponsorImg
            Glide.with(imgSponsor)
                .load(sponsporImgPath)
                .into(imgSponsor)
        }

        imgNavBack.setOnClickListener{
            finish()
        }

    }
}