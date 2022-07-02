package com.app.amigo.Handler;

import com.app.amigo.Models.Trends.CreateRequest.CreateRequestExample;

public interface GoogleHandler {
    public void onSuccess(CreateRequestExample createRequestExample);
    public void onError(String message);
}
