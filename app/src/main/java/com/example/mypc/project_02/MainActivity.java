package com.example.mypc.project_02;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.mypc.project_02.adapter.AdtAlbum;
import com.example.mypc.project_02.model.Album;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdtAlbum.AlbumClickListener {

    public static final String SELECT_ALBUM_ID = "com.example.mypc.project02.selectedalbum";
    RecyclerView rcvListAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initEvents();
    }

    private void initEvents() {

    }

    private void initControls() {
        rcvListAlbums = (RecyclerView) findViewById(R.id.rcvListAlbums);
        AdtAlbum adtAlbum = new AdtAlbum(dummyData(), this, this);

        rcvListAlbums.setLayoutManager(new GridLayoutManager(this, 2));
        rcvListAlbums.setAdapter(adtAlbum);
    }

    public List<Album> dummyData() {
        List<Album> lstAlbum = new ArrayList<>();
        lstAlbum.add(new Album("album1", "album1", "album1", "albumcover1"));
        lstAlbum.add(new Album("album2", "album1", "album1", "albumcover1"));
        lstAlbum.add(new Album("album3", "album1", "album1", "albumcover1"));
        lstAlbum.add(new Album("album4", "album1", "album1", "albumcover1"));
        lstAlbum.add(new Album("album5", "album1", "album1", "albumcover1"));
        lstAlbum.add(new Album("album6", "album1", "album1", "albumcover1"));
        lstAlbum.add(new Album("album7", "album1", "album1", "albumcover1"));
        lstAlbum.add(new Album("album8", "album1", "album1", "albumcover1"));
        return lstAlbum;
    }

    @Override
    public void onClick(String albumId) {
        // Move to Album detail activity
        Intent itAlbumDetail = new Intent(this, ActAlbumDetail.class);
        itAlbumDetail.putExtra(SELECT_ALBUM_ID, albumId);
        startActivity(itAlbumDetail);
    }
}
