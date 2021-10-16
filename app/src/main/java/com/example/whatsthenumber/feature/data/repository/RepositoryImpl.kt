package com.example.whatsthenumber.feature.data.repository

import com.example.whatsthenumber.feature.data.remote.data_source.NumberRemoteDataSource
import com.example.whatsthenumber.feature.domain.Repository

class RepositoryImpl(private val remoteDataSource: NumberRemoteDataSource) : Repository {

    override fun getNumber() = remoteDataSource.getNumber()

}