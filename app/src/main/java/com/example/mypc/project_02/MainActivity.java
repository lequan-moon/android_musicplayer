package com.example.mypc.project_02;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.mypc.project_02.adapter.AdtAlbum;
import com.example.mypc.project_02.model.Album;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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
        AdtAlbum adtAlbum = new AdtAlbum(dummyData(), this);

        rcvListAlbums.setLayoutManager(new LinearLayoutManager(this));
        rcvListAlbums.setAdapter(adtAlbum);
    }

    public List<Album> dummyData() {
        List<Album> lstAlbum = new ArrayList<>();
        lstAlbum.add(new Album("album1", "album1", "albumcover1"));
        return lstAlbum;
    }
}
