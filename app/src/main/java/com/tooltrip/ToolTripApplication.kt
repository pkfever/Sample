package com.tooltrip

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToolTripApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}