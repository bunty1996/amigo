package com.app.amigo.Models.Followers;

public class FollowersModelClass {
    int image;
    String name;

    public FollowersModelClass(int image, String name) {
        this.image =image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
