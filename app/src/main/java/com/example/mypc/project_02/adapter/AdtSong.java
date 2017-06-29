package com.example.mypc.project_02.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypc.project_02.R;
import com.example.mypc.project_02.model.Song;

import java.util.List;

/**
 * Created by QuanLM on 6/27/2017.
 */

public class AdtSong extends RecyclerView.Adapter<AdtSong.SongViewHolder> {
    Context mContext;
    List<Song> lstSong;
    OnPlaySongClick playSongListerner;

    public AdtSong(Context mContext, List<Song> lstSong) {
        this.mContext = mContext;
        this.lstSong = lstSong;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View songView = LayoutInflater.from(mContext).inflate(R.layout.song_item, parent, false);
        SongViewHolder songViewHolder = new SongViewHolder(songView);
        return songViewHolder;
    }

    @Override
    public void onBindViewHolder(final SongViewHolder holder, int position) {
        Song song = lstSong.get(position);
        holder.txtSongId.setText(song.getSongId());
        holder.txtSongArtist.setText(song.getSongArtist());
        holder.txtSongName.setText(song.getSongName());
        if (song.getSongPic() != null) {
            holder.imgSongPic.setImageBitmap(BitmapFactory.decodeByteArray(song.getSongPic(), 0, song.getSongPic().length));
        }else {
            // TODO: Set default song pic
        }

        holder.btnPlaySong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playSongListerner != null) {
                    playSongListerner.onClick(holder.txtSongId.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstSong.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder{
        TextView txtSongName;
        TextView txtSongArtist;
        TextView btnPlaySong;
        TextView txtSongId;
        ImageView imgSongPic;

        public SongViewHolder(View itemView) {
            super(itemView);
            txtSongName = (TextView) itemView.findViewById(R.id.txtSongName);
            txtSongArtist = (TextView) itemView.findViewById(R.id.txtSongArtist);
            btnPlaySong = (TextView) itemView.findViewById(R.id.btnPlaySong);
            txtSongId = (TextView) itemView.findViewById(R.id.txtSongId);
            imgSongPic = (ImageView) itemView.findViewById(R.id.imgSongPic);
        }
    }

    public interface OnPlaySongClick{
        void onClick(String songId);
    }

    public OnPlaySongClick getPlaySongListerner() {
        return playSongListerner;
    }

    public void setPlaySongListerner(OnPlaySongClick playSongListerner) {
        this.playSongListerner = playSongListerner;
    }
}
