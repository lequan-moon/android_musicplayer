package com.example.mypc.project_02.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mypc.project_02.service.ServiceMusicPlayer;

/**
 * Created by MyPC on 30/06/2017.
 */

public class RcvMusicServiceBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String songId = "";
        switch (action) {
            case ServiceMusicPlayer.ACTION_PLAY_SONG:
                songId = intent.getStringExtra(ServiceMusicPlayer.ACTION_PLAY_SONG);
                break;
            case ServiceMusicPlayer.ACTION_PAUSE_SONG:
                Toast.makeText(context, "PAUSED", Toast.LENGTH_SHORT).show();
                break;
            case ServiceMusicPlayer.ACTION_NEXT_SONG:
                Toast.makeText(context, "NEXT SONG", Toast.LENGTH_SHORT).show();
                break;
            case ServiceMusicPlayer.ACTION_BACK_SONG:
                Toast.makeText(context, "PREVIOUS SONG", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        Toast.makeText(context, "Broadcasting: " + songId, Toast.LENGTH_SHORT).show();
    }
}
