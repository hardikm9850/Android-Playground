package com.hardik.playground.coroutineTest

import android.arch.lifecycle.ViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Test
import kotlin.system.measureTimeMillis

class MainViewModel : ViewModel() {

    private var isSessionExpired = false

    suspend fun checkSessionExpiry(): Boolean {
        withContext(Dispatchers.IO) {
            delay(5_000) // to simulate a heavy weight operations
            isSessionExpired = true
        }
        return isSessionExpired
    }
}

class MainViewModelTest {

    @Test
    fun testCheckSessionExpiry() = runBlocking {
        val mainViewModel = MainViewModel()

        val totalExecutionTime = measureTimeMillis {
            val isSessionExpired = mainViewModel.checkSessionExpiry()
            assertTrue(isSessionExpired)
        }

        print("Total Execution Time: $totalExecutionTime")
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testCheckSessionExpiry2() = runTest {
        val mainViewModel = MainViewModel()

        val totalExecutionTime = measureTimeMillis {
            val isSessionExpired = mainViewModel.checkSessionExpiry()
            advanceTimeBy(5000)
            assertTrue(isSessionExpired)
        }

        print("Total Execution Time: $totalExecutionTime")
    }

}