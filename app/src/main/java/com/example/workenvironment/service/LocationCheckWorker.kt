package com.example.workenvironment.service

import android.app.NotificationManager
import android.content.Context
import android.location.Location
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.LocationServices

class LocationCheckWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(applicationContext)

        try {
            val locationResult = fusedLocationClient.lastLocation
            if (locationResult != null) {
                // Check if the employee's location is within the designated work area
                if (isLocationWithinWorkArea(locationResult.result)) {
                    // Employee is within work area
                    sendNotification("You are within the work area")
                } else {
                    // Employee has left work area
                    sendNotification("You have left the work area")
                }
            } else {
                sendNotification("Unable to get location")
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    private fun isLocationWithinWorkArea(location: Location): Boolean {
        val workLocation = Location("Work Location")
        workLocation.latitude = 37.4219983
        workLocation.longitude = -122.084
        val distance = location.distanceTo(workLocation)
        return distance > 1000
    }

    private fun sendNotification(message: String) {
        // Implement logic to send a notification to the user
//        val notification = NotificationCompat.Builder(this, "channelId")
//            .setContentTitle("Work Tracker")
//            .setContentText(message)
//            .build()
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(notificationId, notification)
//    }
    }
}