package com.example.whatsthenumber.feature.domain

import kotlinx.coroutines.flow.Flow

class GetNumberUseCase(private val repository: Repository) {

    operator fun invoke(): Flow<Int> = repository.getNumber()
}