package com.hardik.playground.work_manager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(appContext: Context, val workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("UploadWorker", Thread.currentThread().name)
        // Do the work here--in this case, upload the images.
        //uploadImages()

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}
