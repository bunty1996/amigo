
package com.app.amigo.Models.Trends.Userlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrendsGallery {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
