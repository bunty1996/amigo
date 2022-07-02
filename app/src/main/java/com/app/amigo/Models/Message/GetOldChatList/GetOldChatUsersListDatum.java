
package com.app.amigo.Models.Message.GetOldChatList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetOldChatUsersListDatum {

    @SerializedName("receiver")
    @Expose
    private String receiver;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("lastMsg")
    @Expose
    private String lastMsg;
    @SerializedName("profileImageName")
    @Expose
    private String profileImageName;
    @SerializedName("status")
    @Expose
    private String status;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getProfileImageName() {
        return profileImageName;
    }

    public void setProfileImageName(String profileImageName) {
        this.profileImageName = profileImageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
