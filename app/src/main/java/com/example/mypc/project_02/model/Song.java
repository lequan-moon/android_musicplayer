package com.example.mypc.project_02.model;

/**
 * Created by QuanLM on 6/28/2017.
 */

public class Song {
    String songId;
    String songName;
    String songArtist;
    byte[] songPic;

    public Song(String songId, String songName, String songArtist, byte[] songPic) {
        this.songId = songId;
        this.songName = songName;
        this.songArtist = songArtist;
        this.songPic = songPic;
    }

    public Song(String songId, String songName, String songArtist) {
        this.songId = songId;
        this.songName = songName;
        this.songArtist = songArtist;
    }

    public Song(String songId, String songName) {
        this.songId = songId;
        this.songName = songName;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public byte[] getSongPic() {
        return songPic;
    }

    public void setSongPic(byte[] songPic) {
        this.songPic = songPic;
    }
}
