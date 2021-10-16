package com.example.whatsthenumber.feature.data.remote.service

import com.example.whatsthenumber.feature.data.remote.model.NumberResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface NumberService {

    @GET("rand?min=1&max=300")
    suspend fun getNumber(): NumberResponse
}