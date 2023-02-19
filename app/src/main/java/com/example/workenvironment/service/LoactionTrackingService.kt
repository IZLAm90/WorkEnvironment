package com.example.workenvironment.service

import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import com.example.workenvironment.MainActivity
import com.google.android.gms.location.*

class LoactionTrackingService : Service() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate() {
        super.onCreate()
        startForeground()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        getLocationUpdates()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun getLocationUpdates() {
        val locationRequest = LocationRequest()
            .setInterval(10000)
            .setFastestInterval(5000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    locationResult ?: return
                    // Handle location updates here
                }
            },
            Looper.getMainLooper()
        )
    }

    private fun startForeground() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val notification = NotificationCompat.Builder(this, "location_tracking")
            .setContentTitle("Location tracking is running")
            .setContentText("Your location is being tracked")
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
    }

    override fun startForegroundService(service: Intent?): ComponentName? {
        return super.startForegroundService(service)
    }

    override fun onBind(p0: Intent?): IBinder? {
      return null
    }

    override fun onDestroy() {
        super.onDestroy()
        startForeground()
    }
}