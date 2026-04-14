package com.example.studentproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentproject.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val progressLD = MutableLiveData<Boolean>()
    val TAG = "volleytag"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadErrorLD.value = false // awalnya anggap tidak ada error
        progressLD.value = true // data di load, maka munculkan progress bar

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"

        val stringRequest = StringRequest(Request.Method.GET, url,
            {
                val sType = object: TypeToken<List<Student>>(){ }.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList
            },
            {
                loadErrorLD.value = true
                Log.e("volley_status", it.message.toString())
            })

        stringRequest.tag = TAG

        queue?.add(stringRequest)
        progressLD.value = false
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}