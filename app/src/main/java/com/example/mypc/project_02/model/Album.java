package com.example.mypc.project_02.model;

/**
 * Created by MyPC on 25/06/2017.
 */

public class Album {
    String txtAlbumId;
    String txtAlbumName;
    String txtAlbumDescription;
    String txtAlbumPic;

    public Album(String txtAlbumId, String txtAlbumName, String txtAlbumDescription, String txtAlbumPic) {
        this.txtAlbumId = txtAlbumId;
        this.txtAlbumName = txtAlbumName;
        this.txtAlbumDescription = txtAlbumDescription;
        this.txtAlbumPic = txtAlbumPic;
    }

    public String getTxtAlbumId() {
        return txtAlbumId;
    }

    public void setTxtAlbumId(String txtAlbumId) {
        this.txtAlbumId = txtAlbumId;
    }

    public String getTxtAlbumName() {
        return txtAlbumName;
    }

    public void setTxtAlbumName(String txtAlbumName) {
        this.txtAlbumName = txtAlbumName;
    }

    public String getTxtAlbumDescription() {
        return txtAlbumDescription;
    }

    public void setTxtAlbumDescription(String txtAlbumDescription) {
        this.txtAlbumDescription = txtAlbumDescription;
    }

    public String getTxtAlbumPic() {
        return txtAlbumPic;
    }

    public void setTxtAlbumPic(String txtAlbumPic) {
        this.txtAlbumPic = txtAlbumPic;
    }
}
