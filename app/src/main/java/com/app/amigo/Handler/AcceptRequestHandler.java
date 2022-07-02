package com.app.amigo.Handler;

import com.app.amigo.Models.Additionalinformation.AditionalInformationExample;
import com.app.amigo.Models.Followers.AcceptRequest.AcceptRequestExample;

public interface AcceptRequestHandler {
    public void onSuccess(AcceptRequestExample acceptRequestExample);
    public void onError(String message);
}
