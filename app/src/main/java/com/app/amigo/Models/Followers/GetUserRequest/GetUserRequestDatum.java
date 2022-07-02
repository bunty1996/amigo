
package com.app.amigo.Models.Followers.GetUserRequest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserRequestDatum {

    @SerializedName("reqId")
    @Expose
    private String reqId;
    @SerializedName("reqBy")
    @Expose
    private ReqBy reqBy;
    @SerializedName("reqTo")
    @Expose
    private ReqTo reqTo;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public ReqBy getReqBy() {
        return reqBy;
    }

    public void setReqBy(ReqBy reqBy) {
        this.reqBy = reqBy;
    }

    public ReqTo getReqTo() {
        return reqTo;
    }

    public void setReqTo(ReqTo reqTo) {
        this.reqTo = reqTo;
    }

}
