package com.app.amigo.Models.Message;

public class MessagesModelClass {
    int img_ciuserprofile;
    String tv_usersname;
    String tv_messages;
    String tv_messagecount;

    public MessagesModelClass(int men, String ankit, String hey_how_are_you, String s) {
        this.img_ciuserprofile = men;
        this.tv_usersname = ankit;
        this.tv_messages = hey_how_are_you;
        this.tv_messagecount = s;

    }

    public int getImg_ciuserprofile() {
        return img_ciuserprofile;
    }

    public void setImg_ciuserprofile(int img_ciuserprofile) {
        this.img_ciuserprofile = img_ciuserprofile;
    }

    public String getTv_usersname() {
        return tv_usersname;
    }

    public void setTv_usersname(String tv_usersname) {
        this.tv_usersname = tv_usersname;
    }

    public String getTv_messages() {
        return tv_messages;
    }

    public void setTv_messages(String tv_messages) {
        this.tv_messages = tv_messages;
    }

    public String getTv_messagecount() {
        return tv_messagecount;
    }

    public void setTv_messagecount(String tv_messagecount) {
        this.tv_messagecount = tv_messagecount;
    }
}
