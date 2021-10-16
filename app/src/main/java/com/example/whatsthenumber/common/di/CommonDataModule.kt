package com.example.whatsthenumber.common.di

import com.example.whatsthenumber.BuildConfig
import com.example.whatsthenumber.common.network.WebServiceFactory
import org.koin.dsl.module

val commonDataModule = module {

    single {
        WebServiceFactory.provideOkHttpClient(
            BuildConfig.DEBUG && BuildConfig.BUILD_TYPE == "debug"
        )
    }
}