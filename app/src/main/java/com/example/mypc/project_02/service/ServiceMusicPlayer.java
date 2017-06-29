package com.example.mypc.project_02.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.mypc.project_02.ActAlbumDetail;

import java.io.IOException;

/**
 * Created by QuanLM on 6/29/2017.
 */

public class ServiceMusicPlayer extends Service {
    Context mContext;
    IBinder bindMusicPlayer = new MusicPlayerBinder();
    MediaPlayer musicPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayer = new MediaPlayer();
        mContext = getApplicationContext();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bindMusicPlayer;
    }

    public class MusicPlayerBinder extends Binder{
        private static final int MUSIC_PLAYER_NOTIFY_ID = 0;

        public ServiceMusicPlayer getService() {
            return ServiceMusicPlayer.this;
        }

        public void playSong(String filePath) throws IOException {
            musicPlayer.setDataSource(filePath);
            musicPlayer.prepare();
            musicPlayer.start();

            Intent itAlbumDetail = new Intent(mContext, ActAlbumDetail.class);
            itAlbumDetail.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntAlbumDetail = PendingIntent.getActivity(mContext, 0, itAlbumDetail, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Builder notibuilder = new Notification.Builder(mContext);
            notibuilder.setSmallIcon(android.R.drawable.ic_media_pause)
                    .setOngoing(true)
                    .setContentTitle(filePath)
                    .setContentText("Playing " + filePath);
            NotificationManager notiManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notiManager.notify(MUSIC_PLAYER_NOTIFY_ID, notibuilder.build());

        }

        public boolean isPlaying() {
            return musicPlayer.isPlaying();
        }

    }
}
