
package com.app.amigo.Models.Additionalinformation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AditionalInformationExample {

    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("data")
    @Expose
    private AditionalInformationData data;

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public AditionalInformationData getData() {
        return data;
    }

    public void setData(AditionalInformationData data) {
        this.data = data;
    }

}
