package com.example.whatsthenumber.feature.di

import com.example.whatsthenumber.feature.presentation.WhatsTheNumberViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        WhatsTheNumberViewModel(
            getNumberUseCase = get()
        )
    }
}
