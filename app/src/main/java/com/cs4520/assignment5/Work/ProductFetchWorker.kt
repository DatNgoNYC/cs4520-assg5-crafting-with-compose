package com.cs4520.assignment5.Work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cs4520.assignment4.Data.ProductRepository

class FetchProductsWorker (appContext: Context, workerParameters: WorkerParameters):
    CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result {
        return try {
            val repository: ProductRepository = ProductRepository(applicationContext)
            repository.addRandomProductListToCurrentAndReturnAllProducts()
            Log.d("WorkerManager", "adding stuff with workermanager")


            Result.success()
        } catch (e: Error) {
            Log.d("WorkerManager", "${e.message}")
            Result.failure()
        }

    }

}