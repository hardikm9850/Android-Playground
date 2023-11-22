package com.hardik.playground.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

class MainViewModel {
    var viewModelScope = CloseableCoroutineScope()

    fun runTask() {
        var i = 5
        val context = viewModelScope.coroutineContext
        viewModelScope.launch {
            while (i > 0) {
                println("running $i")
                delay(4000)
                i--
            }
        }
    }

    fun cancelScope() {
        viewModelScope.cancel()
    }
}

class CloseableCoroutineScope() : Closeable, CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main

    override fun close() {
        coroutineContext.cancel()
    }
}
