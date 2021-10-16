package com.example.whatsthenumber.feature.data.remote.model

import com.google.gson.annotations.SerializedName

data class NumberResponse(
    @SerializedName("value")
    val number: Int = 0
)