package com.example.whatsthenumber.feature.di

import com.example.whatsthenumber.feature.domain.GetNumberUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetNumberUseCase(
            repository = get()
        )
    }
}