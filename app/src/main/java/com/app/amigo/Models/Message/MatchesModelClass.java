package com.app.amigo.Models.Message;

public class MatchesModelClass {
    int ciprofileimage;
    int oval;
    String name;

    public MatchesModelClass(int men, int oval, String ankit) {
        this.ciprofileimage = men;
        this.oval = oval;
        this.name=ankit;
    }

    public int getCiprofileimage() {
        return ciprofileimage;
    }

    public void setCiprofileimage(int ciprofileimage) {
        this.ciprofileimage = ciprofileimage;
    }

    public int getOval() {
        return oval;
    }

    public void setOval(int oval) {
        this.oval = oval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
