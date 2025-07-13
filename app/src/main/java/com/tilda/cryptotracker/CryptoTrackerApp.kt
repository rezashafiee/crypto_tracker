package com.tilda.cryptotracker

import android.app.Application
import com.tilda.cryptotracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = this@CryptoTrackerApp)
            androidLogger()
            modules(appModule)
        }
    }
}