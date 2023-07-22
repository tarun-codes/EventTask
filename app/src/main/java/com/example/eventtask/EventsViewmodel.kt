package com.example.eventtask


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventtask.model.AgendaResponse
import com.example.eventtask.model.Events
import com.example.eventtask.retrofit.PostRequestBody
import com.example.eventtask.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KProperty

class EventsViewmodel : ViewModel() {

    private val apiService = RetrofitClient.apiService

    private val _responseData = MutableLiveData<Events>()
    val responseData: LiveData<Events> = _responseData

    private val _agendaResponseData = MutableLiveData<AgendaResponse>()
    val agendaResponseData: LiveData<AgendaResponse> = _agendaResponseData


    fun getEvents() {
        viewModelScope.launch(Dispatchers.IO) {


            val requestBody = PostRequestBody("1989", "117195")
            val call = apiService.postAgenda(requestBody)

            call.enqueue(object : Callback<Events> {
                override fun onResponse(call: Call<Events>, response: Response<Events>) {
                    if (response.isSuccessful) {
                        val yourData = response.body()
                        Log.d("response_json", "success")
                        _responseData.postValue(yourData)
                    } else {
                        Log.d("response_json", "error")
                        // Handle error
                    }
                }

                override fun onFailure(call: Call<Events>, t: Throwable) {
                    Log.d("response_json", "error")
                }
            })
        }
    }


    fun getAgendaDetails(aid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val call = apiService.getAgendaDetails(aid)

            call.enqueue(object : Callback<AgendaResponse> {
                override fun onResponse(
                    call: Call<AgendaResponse>,
                    response: Response<AgendaResponse>
                ) {
                    if (response.isSuccessful) {
                        val yourData = response.body()
                        _agendaResponseData.postValue(yourData)
                    } else {

                    }
                }

                override fun onFailure(call: Call<AgendaResponse>, t: Throwable) {

                }
            })
        }

    }
}

