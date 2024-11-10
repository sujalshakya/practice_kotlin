package base.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Created to override application to add hilt throughout app.
@HiltAndroidApp
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}