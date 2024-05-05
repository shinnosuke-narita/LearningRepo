package com.example.project

import android.app.Application
import com.example.project.di.KoinInit
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KoinInit().execute {
            androidContext(this@MainApplication)
        }
    }
}