package com.example.whatsthenumber.common.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object WebServiceFactory {

    inline fun <reified T> getRetrofitInstance(url: String, okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create()
    }

    fun provideOkHttpClient(wasDebugVersion: Boolean): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .httpLoggingInterceptor(wasDebugVersion)
            .build()

    private fun OkHttpClient.Builder.httpLoggingInterceptor(wasDebugVersion: Boolean) =
        when (wasDebugVersion) {
            true -> {
                val interceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(interceptor)
            }
            else -> this
        }
}

