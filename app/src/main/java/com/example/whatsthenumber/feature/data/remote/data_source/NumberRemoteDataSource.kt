package com.example.whatsthenumber.feature.data.remote.data_source

import com.example.whatsthenumber.feature.data.remote.service.NumberService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NumberRemoteDataSource(private val service: NumberService) {
    fun getNumber(): Flow<Int> = flow {
        emit(service.getNumber().number)
    }
}