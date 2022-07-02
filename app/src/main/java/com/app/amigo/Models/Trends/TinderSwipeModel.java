package com.app.amigo.Models.Trends;

public class TinderSwipeModel {

    private String courseName;
    private String courseDuration;
    private String courseTracks;
    private String courseDescription;
    private int imgId;

    public TinderSwipeModel(String s, String s1, String s2, String s3, int men) {
        this.courseName = s;
        this.courseDuration = s1;
        this.courseTracks = s2;
        this.courseDescription = s3;
        this.imgId = men;

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseTracks() {
        return courseTracks;
    }

    public void setCourseTracks(String courseTracks) {
        this.courseTracks = courseTracks;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
