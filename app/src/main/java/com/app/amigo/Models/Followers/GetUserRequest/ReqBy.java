
package com.app.amigo.Models.Followers.GetUserRequest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqBy {

    @SerializedName("reqBy")
    @Expose
    private String reqBy;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picUrl")
    @Expose
    private String picUrl;

    public String getReqBy() {
        return reqBy;
    }

    public void setReqBy(String reqBy) {
        this.reqBy = reqBy;
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
