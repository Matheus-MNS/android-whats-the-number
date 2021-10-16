package com.example.whatsthenumber.base_app

import android.app.Application
import com.example.whatsthenumber.common.di.commonDataModule
import com.example.whatsthenumber.feature.di.whatsTheNumberModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                whatsTheNumberModule+
                        commonDataModule
            ).androidContext(applicationContext)
        }
    }
}