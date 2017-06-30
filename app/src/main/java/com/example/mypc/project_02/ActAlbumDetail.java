package com.example.mypc.project_02;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mypc.project_02.adapter.AdtSong;
import com.example.mypc.project_02.model.Song;
import com.example.mypc.project_02.receiver.RcvMusicServiceBroadcast;
import com.example.mypc.project_02.service.ServiceMusicPlayer;
import com.example.mypc.project_02.ulti.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActAlbumDetail extends AppCompatActivity implements AdtSong.OnPlaySongClick {
    ImageView imgAlbumPic;
    RecyclerView lstSong;
    ServiceMusicPlayer.MusicPlayerBinder musicPlayerBinder;
    boolean isServiceBound = false;

    ServiceConnection connMusicPlayerService = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(ActAlbumDetail.this, "selected album:", Toast.LENGTH_SHORT).show();
            musicPlayerBinder = (ServiceMusicPlayer.MusicPlayerBinder) service;
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (isServiceBound){
                isServiceBound = false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_album_detail);

        initControls();
        initEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent itMusicPlayer = new Intent(this, ServiceMusicPlayer.class);
        bindService(itMusicPlayer, connMusicPlayerService, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connMusicPlayerService);
    }

    private void initEvents() {
        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActAlbumDetail.this, "Play album", Toast.LENGTH_SHORT).show();
            }
        });
        IntentFilter intFilter = new IntentFilter();
        intFilter.addAction(ServiceMusicPlayer.ACTION_PLAY_SONG);
        intFilter.addAction(ServiceMusicPlayer.ACTION_PAUSE_SONG);
        intFilter.addAction(ServiceMusicPlayer.ACTION_NEXT_SONG);
        intFilter.addAction(ServiceMusicPlayer.ACTION_BACK_SONG);
        registerReceiver(new RcvMusicServiceBroadcast(), intFilter);
    }

    private void initControls() {
        imgAlbumPic = (ImageView) findViewById(R.id.imgAlbumPic);
        lstSong = (RecyclerView) findViewById(R.id.lstSong);
        List<Song> lstSongInAlbum = getSongList();
        AdtSong songAdt = new AdtSong(this, lstSongInAlbum);
        songAdt.setPlaySongListerner(this);

        lstSong.setLayoutManager(new LinearLayoutManager(this));
        lstSong.setAdapter(songAdt);
    }

    public List<Song> getSongList() {
        String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();

        List<Song> lstSongsInAlbum = new ArrayList<>();
        List<File> physicalFiles = FileHelper.getListFilesMp3(new File(sdcardPath));

        for (File musicFile : physicalFiles) {
            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
            metadataRetriever.setDataSource(musicFile.getPath());
            String songId = musicFile.getPath();
            String songName = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            if (songName == null){
                songName = musicFile.getName();
            }
            String songArtist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            if(songArtist == null){
                songArtist = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
            }
            byte[] songPic = metadataRetriever.getEmbeddedPicture();
            lstSongsInAlbum.add(new Song(songId, songName, songArtist, songPic));
        }

        return lstSongsInAlbum;
    }

    @Override
    public void onClick(String songId) {
        if(!isServiceBound)
            return;
        try {
            musicPlayerBinder.playSong(songId);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "File " + songId + " not found!", Toast.LENGTH_SHORT).show();
        }
    }

}
