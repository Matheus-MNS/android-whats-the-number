package com.example.whatsthenumber.feature.di

import com.example.whatsthenumber.common.network.BASE_URL
import com.example.whatsthenumber.common.network.WebServiceFactory
import com.example.whatsthenumber.feature.data.remote.data_source.NumberRemoteDataSource
import com.example.whatsthenumber.feature.data.remote.service.NumberService
import com.example.whatsthenumber.feature.data.repository.RepositoryImpl
import com.example.whatsthenumber.feature.domain.Repository
import org.koin.dsl.module

val dataModule = module {

    single {
        WebServiceFactory.getRetrofitInstance(
            url = BASE_URL,
            okHttpClient = get()
        ) as NumberService
    }

    factory {
        NumberRemoteDataSource(
            service = get()
        )
    }

    factory<Repository> {
        RepositoryImpl(
            remoteDataSource = get()
        )
    }
}