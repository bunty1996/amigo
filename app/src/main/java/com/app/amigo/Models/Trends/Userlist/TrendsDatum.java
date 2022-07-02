
package com.app.amigo.Models.Trends.Userlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrendsDatum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("profileImageName")
    @Expose
    private String profileImageName;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("aboutMe")
    @Expose
    private String aboutMe;
    @SerializedName("livingIn")
    @Expose
    private String livingIn;
    @SerializedName("myWork")
    @Expose
    private String myWork;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("favSports")
    @Expose
    private String favSports;
    @SerializedName("degreeOfEduction")
    @Expose
    private String degreeOfEduction;
    @SerializedName("lookingFor")
    @Expose
    private String lookingFor;
    @SerializedName("sexualOrientation")
    @Expose
    private String sexualOrientation;
    @SerializedName("loc")
    @Expose
    private TrendsLoc loc;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gallery")
    @Expose
    private List<TrendsGallery> gallery = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfileImageName() {
        return profileImageName;
    }

    public void setProfileImageName(String profileImageName) {
        this.profileImageName = profileImageName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getLivingIn() {
        return livingIn;
    }

    public void setLivingIn(String livingIn) {
        this.livingIn = livingIn;
    }

    public String getMyWork() {
        return myWork;
    }

    public void setMyWork(String myWork) {
        this.myWork = myWork;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFavSports() {
        return favSports;
    }

    public void setFavSports(String favSports) {
        this.favSports = favSports;
    }

    public String getDegreeOfEduction() {
        return degreeOfEduction;
    }

    public void setDegreeOfEduction(String degreeOfEduction) {
        this.degreeOfEduction = degreeOfEduction;
    }

    public String getLookingFor() {
        return lookingFor;
    }

    public void setLookingFor(String lookingFor) {
        this.lookingFor = lookingFor;
    }

    public String getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(String sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

    public TrendsLoc getLoc() {
        return loc;
    }

    public void setLoc(TrendsLoc loc) {
        this.loc = loc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TrendsGallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<TrendsGallery> gallery) {
        this.gallery = gallery;
    }

}
