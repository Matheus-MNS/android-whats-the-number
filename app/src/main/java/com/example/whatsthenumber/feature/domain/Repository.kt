package com.example.whatsthenumber.feature.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getNumber(): Flow<Int>
}