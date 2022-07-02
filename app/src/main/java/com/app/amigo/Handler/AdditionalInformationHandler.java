package com.app.amigo.Handler;

import com.app.amigo.Models.Additionalinformation.AditionalInformationExample;

public interface AdditionalInformationHandler {
    public void onSuccess(AditionalInformationExample additionalInformationExample);
    public void onError(String message);
}
