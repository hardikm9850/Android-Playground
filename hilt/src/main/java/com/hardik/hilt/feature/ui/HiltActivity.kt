package com.hardik.hilt.feature.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * @AndroidEntryPoint generates an individual Hilt component for each Android class in the project.
 * These components can receive dependencies from their respective parent classes
 */
@AndroidEntryPoint
class HiltActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}