package com.sck.mvvm.workermanager

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.work.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

val TAG="sck220"
val KEY="params"

class Demo {

    fun aa(context: Context,lifecycleOwner: LifecycleOwner){
        val request= OneTimeWorkRequestBuilder<TestWorker>()
            .build()
        val result=WorkManager.getInstance(context)
            .enqueueUniqueWork("sck220",ExistingWorkPolicy.REPLACE,request)
    }
}

class TestWorker(context: Context,workerParameters: WorkerParameters):Worker(context,workerParameters){
    override fun doWork(): Result {
        Log.i(
            TAG, "doWork: inputData TestWorker ${inputData.getInt(
                KEY,-1)}")
        Thread.sleep(4000)
        writeText(applicationContext)
        Log.i(TAG, "22")
        return Result.success(workDataOf(KEY +"1" to 10))
    }

}

class TestWorker1(context: Context,workerParameters: WorkerParameters):Worker(context,workerParameters){
    override fun doWork(): Result {
        Log.i(
            TAG, "doWork: inputData TestWorker1 ${inputData.getInt(
                KEY,-1)}")
        Thread.sleep(3000)
        writeText(applicationContext)
        return Result.success(workDataOf(KEY +"2" to 20))
    }
}

class TestWorker2(context: Context,workerParameters: WorkerParameters):Worker(context,workerParameters){
    override fun doWork(): Result {

        for((key,value) in inputData.keyValueMap){
            Log.i(TAG, "doWork: ${key} = ${value}")
        }
        Thread.sleep(3000)
        writeText(applicationContext)
        return Result.success(workDataOf(KEY to 30))
    }
}


private fun writeText(context: Context){
    val file= File(context.externalCacheDir,"demo.txt")
    file.appendText(SimpleDateFormat("HH:mm:ss").format(Date()))
    file.appendText("\n")
}


