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
import android.widget.RemoteViews;

import com.example.mypc.project_02.ActAlbumDetail;
import com.example.mypc.project_02.R;

import java.io.IOException;

/**
 * Created by QuanLM on 6/29/2017.
 */

public class ServiceMusicPlayer extends Service {
    private static final int MUSIC_PLAYER_NOTIFY_ID = 0;
    public static final String ACTION_PLAY_SONG = "com.example.mypc.service.broadcast.play_song";
    public static final String ACTION_PAUSE_SONG = "com.example.mypc.service.broadcast.pause_song";
    public static final String ACTION_NEXT_SONG = "com.example.mypc.service.broadcast.next_song";
    public static final String ACTION_BACK_SONG = "com.example.mypc.service.broadcast.back_song";
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

    public void buildNotification(String filePath) {

        RemoteViews customNotify = new RemoteViews(getPackageName(), R.layout.custom_notification);
        customNotify.setTextViewText(R.id.txtSongName, filePath);

        Intent itPlay = new Intent(mContext, ServiceMusicPlayer.class);
        itPlay.setAction(ACTION_PLAY_SONG);
        PendingIntent pditPlay = PendingIntent.getActivity(mContext, 0, itPlay, 0);
        customNotify.setOnClickPendingIntent(R.id.btnPlay, pditPlay);
        Intent itBack = new Intent(mContext, ServiceMusicPlayer.class);
        itBack.setAction(ACTION_BACK_SONG);
        PendingIntent pditBack = PendingIntent.getActivity(mContext, 0, itBack, 0);
        customNotify.setOnClickPendingIntent(R.id.btnBack, pditBack);
        Intent itNext = new Intent(mContext, ServiceMusicPlayer.class);
        itNext.setAction(ACTION_NEXT_SONG);
        PendingIntent pditNext = PendingIntent.getActivity(mContext, 0, itNext, 0);
        customNotify.setOnClickPendingIntent(R.id.btnNext, pditNext);

        Intent itAlbumDetail = new Intent(mContext, ActAlbumDetail.class);
        itAlbumDetail.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntAlbumDetail = PendingIntent.getActivity(mContext, 0, itAlbumDetail, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder notiBuilder = new Notification.Builder(mContext);
        notiBuilder.
                setContentIntent(pendingIntAlbumDetail)
                .setSmallIcon(android.R.drawable.ic_media_pause)
                .setOngoing(true)
                .setContentTitle(filePath)
                .setCustomContentView(customNotify);
        NotificationManager notiManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notiManager.notify(MUSIC_PLAYER_NOTIFY_ID, notiBuilder.build());
    }

    public class MusicPlayerBinder extends Binder{

        public ServiceMusicPlayer getService() {
            return ServiceMusicPlayer.this;
        }

        public void playSong(String filePath) throws IOException {
            if (musicPlayer.isPlaying()) {
                musicPlayer.reset();
            }
            musicPlayer.setDataSource(filePath);
            musicPlayer.prepare();
            musicPlayer.start();

            buildNotification(filePath);

            Intent itPlayMusic = new Intent();
            itPlayMusic.setAction(ACTION_PLAY_SONG);
            itPlayMusic.putExtra(ACTION_PLAY_SONG, filePath);
            sendBroadcast(itPlayMusic);

        }

        public boolean isPlaying() {
            return musicPlayer.isPlaying();
        }

    }
}
