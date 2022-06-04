package com.example.android_service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private final String LOG_KEY = "LOG_KEY@" + MainActivity.class.getSimpleName();
    private MediaPlayer mediaPlayer;
    private Boolean isPlay;

    @Override
    public void onCreate() {
        super.onCreate();
        this.isPlay = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!this.isPlay) {
            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
            Notification notification = new NotificationCompat.Builder(MyService.this, "testService")
                    .setContentTitle("Example service")
                    .setContentText("Example service")
                    .setSmallIcon(R.drawable.ic_music_note)
                    .setContentIntent(pendingIntent)
                    .build();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music_simple);
            mediaPlayer.start();
            this.isPlay = true;
            startForeground(1, notification);
        }

        //return super.onStartCommand(intent, flags, startId);
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        this.mediaPlayer.stop();
        this.isPlay = false;
        super.onDestroy();
    }
}
