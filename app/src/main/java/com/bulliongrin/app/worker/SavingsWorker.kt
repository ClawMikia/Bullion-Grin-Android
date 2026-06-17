package com.bulliongrin.app.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bulliongrin.app.utils.NotificationHelper

class SavingsWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        NotificationHelper.showNotification(
            applicationContext,
            "Bullion Grin Reminder",
            "Time to add your daily contribution!",
        )
        return Result.success()
    }
}
