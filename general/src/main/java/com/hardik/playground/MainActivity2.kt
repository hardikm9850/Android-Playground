package com.hardik.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardik.playground.ui.main.MainFragment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
class MyViewModel(private val repo: Repository) : ViewModel() {
    fun callRepo() {
        val threadPool = ThreadPoolExecutor(1,2,1000,TimeUnit.MINUTES, PriorityBlockingQueue())
        val threads = 4
        val dispatcher = Executors.newFixedThreadPool(threads).asCoroutineDispatcher()

        viewModelScope.launch(context = threadPool.asCoroutineDispatcher()) {
            repo.doWork()
        }
    }
}
class Repository(private val ioDispatcher: CoroutineDispatcher) {
    suspend fun doWork() {
        withContext(ioDispatcher) {
            //doSomeOtherWork()
            //veryImportantOperation() // This shouldn’t be cancelled
        }
    }
}