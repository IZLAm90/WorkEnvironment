package com.example.workenvironment.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.workenvironment.R
import com.example.workenvironment.utils.PAUSE
import com.example.workenvironment.utils.PLAY
import com.example.workenvironment.utils.STOP
import java.io.IOException


class MediaService : Service(), MediaPlayer.OnCompletionListener {

    private var mediaPlayer: MediaPlayer? = null
    private var mediaTitle: String? = null
    private var mediaUrl: String? = null
    private var notificationManager: NotificationManager? = null

    companion object {
        private const val TAG = "MediaService"
        private const val NOTIFICATION_ID = 1
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand:${intent?.getStringExtra("mediaTitle")} ")
        intent?.let {
            when (it.action) {
                PLAY -> handleActionPlay()
                PAUSE -> handleActionPause()
                STOP -> handleActionStop()
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setOnCompletionListener(this)
        Log.d(TAG, "onCreate: servec ")

    }

    private fun handleActionPlay() {
        if (mediaPlayer?.isPlaying == true) {
            return
        }
        val audioUri = Uri.parse("android.resource://${this.packageName}/raw/${R.raw.s00}")
        Log.d(TAG, "handleActionPlay:${audioUri} ")

        mediaPlayer?.reset()
        try {
            mediaPlayer?.setDataSource(this, audioUri)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        } catch (e: IOException) {
            Log.e(TAG, "Failed to start media playback", e)
        }

        showNotification()
    }

    private fun handleActionPause() {
        if (mediaPlayer?.isPlaying == false) {
            return
        }

        mediaPlayer?.pause()

        updateNotification()
    }

    private fun handleActionStop() {
        if (mediaPlayer?.isPlaying == false) {
            return
        }

        mediaPlayer?.stop()
        stopSelf()
    }

    private fun showNotification() {
        val playIntent = Intent(this, MediaService::class.java)
        playIntent.action = PAUSE
        val playPendingIntent =
            PendingIntent.getService(this, 0, playIntent, PendingIntent.FLAG_MUTABLE)

        val stopIntent = Intent(this, MediaService::class.java)
        stopIntent.action = STOP
        val stopPendingIntent =
            PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_MUTABLE)

        val builder = NotificationCompat.Builder(this, "default")
            .setSmallIcon(R.drawable.ic_visibilit)
            .setContentTitle(mediaTitle)
            .setContentText("Playing now...")
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(R.drawable.ic_play_arrow_24, "Pause", playPendingIntent)
            .addAction(R.drawable.ic_stop_circle_24, "Stop", stopPendingIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(null)
                    .setShowActionsInCompactView(0, 1)
            )
            .setOngoing(true)

            val playIntent2 = Intent(this, MediaService::class.java)
            playIntent.action = PAUSE
            val playPendingIntent2 =
                PendingIntent.getService(this, 0, playIntent2, PendingIntent.FLAG_MUTABLE)

            builder.addAction(R.drawable.ic_play_arrow_24, "Play", playPendingIntent2)


        notificationManager?.notify(NOTIFICATION_ID, builder.build())
    }

    private fun updateNotification() {
        val playIntent = Intent(this, MediaService::class.java)
        playIntent.action = PLAY
        val playPendingIntent =
            PendingIntent.getService(this, 0, playIntent, PendingIntent.FLAG_MUTABLE)

        val stopIntent = Intent(this, MediaService::class.java)
        stopIntent.action = STOP
        val stopPendingIntent =
            PendingIntent.getService(this, 0, stopIntent, PendingIntent.FLAG_MUTABLE)

        val builder = NotificationCompat.Builder(this, "default")
            .setSmallIcon(R.drawable.ic_visibilit)
            .setContentTitle(mediaTitle)
            .setContentText("Paused...")
            .addAction(R.drawable.ic_play_arrow_24, "Play", playPendingIntent)
            .addAction(R.drawable.ic_stop_circle_24, "Stop", stopPendingIntent)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(null)
                    .setShowActionsInCompactView(0, 1)
            )

        notificationManager?.notify(NOTIFICATION_ID, builder.build())
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
        stopForeground(true)
        notificationManager?.cancel(NOTIFICATION_ID)
    }

    override fun onCompletion(mp: MediaPlayer?) {
        stopSelf()
    }

    fun setMediaUrl(mediaUrl: String) {
        this.mediaUrl = mediaUrl
    }

    fun setMediaTitle(mediaTitle: String) {
        this.mediaTitle = mediaTitle
    }
}