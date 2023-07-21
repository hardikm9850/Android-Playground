package com.hardik.playground.async

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.Callable
import java.util.concurrent.Executors


fun main(args: Array<String>) {
    val TAG = "90"
    val asyncTask = object  : CustomAsyncTask<String>(){
        override fun onPreExecute() {
        }

        override fun doInBackground(): String {
            println(" in background "+Thread.currentThread().name)

            return "data from background"
        }

        override fun onPostExecute(result: String) {
            println("data received $result")
        }
    }
    asyncTask.execute()
}



private abstract class CustomAsyncTask<T> {
    abstract fun onPreExecute()
    abstract fun onPostExecute(result: T)
    abstract fun doInBackground(): T
    private val TAG = "81"
    fun execute() {
        val executor = Executors.newSingleThreadExecutor()
        val backgroundThread: BackgroundThread = BackgroundThread()
        try {
            onPreExecute()
        } catch (e: Exception) {
            println("exception e")
        }
        val future = executor.submit(Callable {
            Thread.sleep(10000)
            return@Callable doInBackground()
        })
        val data = future.get()
        onPostExecute(data)
        //backgroundThread.start()
    }

    internal inner class BackgroundThread : Thread() {
        var handler: Handler? = null
        override fun run() {
            Log.d(TAG, "doInBackground Thread name " + currentThread().name)
            handler = Handler(Looper.getMainLooper())
            val result = doInBackground()
            handler!!.post {
                onPostExecute(result)
                Log.d(
                    TAG,
                    "onPostExecute Thread name " + currentThread().name
                )
            }
        }
    }
}
