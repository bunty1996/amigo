
package com.app.amigo.Models.Trends.CreateRequest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CreateRequestData{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fcmToken")
    @Expose
    private String fcmToken;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("reqBy")
    @Expose
    private String reqBy;
    @SerializedName("reqTo")
    @Expose
    private String reqTo;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqBy() {
        return reqBy;
    }

    public void setReqBy(String reqBy) {
        this.reqBy = reqBy;
    }

    public String getReqTo() {
        return reqTo;
    }

    public void setReqTo(String reqTo) {
        this.reqTo = reqTo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
