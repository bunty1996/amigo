
package com.app.amigo.Models.Additionalinformation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AditionalInformationData {

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
    @SerializedName("hieght")
    @Expose
    private String hieght;
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
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("likeBy")
    @Expose
    private List<Object> likeBy = null;
    @SerializedName("following")
    @Expose
    private List<Object> following = null;
    @SerializedName("followers")
    @Expose
    private List<Object> followers = null;
    @SerializedName("favourites")
    @Expose
    private List<Object> favourites = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fcmToken")
    @Expose
    private String fcmToken;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gallery")
    @Expose
    private List<Object> gallery = null;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;

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

    public String getHieght() {
        return hieght;
    }

    public void setHieght(String hieght) {
        this.hieght = hieght;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Object> getLikeBy() {
        return likeBy;
    }

    public void setLikeBy(List<Object> likeBy) {
        this.likeBy = likeBy;
    }

    public List<Object> getFollowing() {
        return following;
    }

    public void setFollowing(List<Object> following) {
        this.following = following;
    }

    public List<Object> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Object> followers) {
        this.followers = followers;
    }

    public List<Object> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Object> favourites) {
        this.favourites = favourites;
    }

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

    public List<Object> getGallery() {
        return gallery;
    }

    public void setGallery(List<Object> gallery) {
        this.gallery = gallery;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
