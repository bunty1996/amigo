
package com.app.amigo.Models.Followers.GetUserRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqTo {

    @SerializedName("reqTo")
    @Expose
    private String reqTo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picUrl")
    @Expose
    private String picUrl;

    public String getReqTo() {
        return reqTo;
    }

    public void setReqTo(String reqTo) {
        this.reqTo = reqTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}
