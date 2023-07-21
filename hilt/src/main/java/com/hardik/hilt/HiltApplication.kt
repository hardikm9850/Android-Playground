package com.hardik.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Setting up the hilt library
 */
/**
 * @HiltAndroidApp triggers Hilt's code generation, including a base class for the application
 * that serves as the application-level dependency container.
 */
@HiltAndroidApp
class HiltApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}