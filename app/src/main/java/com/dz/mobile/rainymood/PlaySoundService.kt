package com.dz.mobile.rainymood

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class PlaySoundService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this,R.raw.rainymood)
        mediaPlayer.isLooping = true
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        mediaPlayer.start()
        playState = true
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        playState = false
    }

    companion object {
        var playState = false
    }
}