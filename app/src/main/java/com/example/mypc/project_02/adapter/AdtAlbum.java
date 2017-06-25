package com.example.mypc.project_02.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mypc.project_02.R;
import com.example.mypc.project_02.model.Album;

import java.util.List;

/**
 * Created by MyPC on 25/06/2017.
 */

public class AdtAlbum extends RecyclerView.Adapter<AdtAlbum.AlbumViewHolder> {
    AlbumClickListener listener;
    List<Album> mListAlbum;
    Context mContext;

    public AdtAlbum(List<Album> mListAlbum, Context mContext, AlbumClickListener listener) {
        this.mListAlbum = mListAlbum;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View albumItemView = LayoutInflater.from(mContext).inflate(R.layout.albums_item, parent, false);
        return new AlbumViewHolder(albumItemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album = mListAlbum.get(position);
        holder.txtAlbumName.setText(album.getTxtAlbumName());
        holder.imgAlbumPic.setBackground(mContext.getResources().getDrawable(R.drawable.album1, null));
    }

    @Override
    public int getItemCount() {
        return mListAlbum.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder{
        TextView txtAlbumId;
        ImageView imgAlbumPic;
        TextView txtAlbumName;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            imgAlbumPic = (ImageView) itemView.findViewById(R.id.imgAlbumPic);
            txtAlbumName = (TextView) itemView.findViewById(R.id.txtAlbumName);
            txtAlbumId = (TextView) itemView.findViewById(R.id.txtAlbumId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onClick();
                    }
                }
            });
        }
    }

    public interface AlbumClickListener{
        void onClick();
    }
}
