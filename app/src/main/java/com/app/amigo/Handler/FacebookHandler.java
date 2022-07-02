package com.app.amigo.Handler;

import com.app.amigo.Models.Trends.CreateRequest.CreateRequestExample;

public interface FacebookHandler {
    public void onSuccess(CreateRequestExample createRequestExample);
    public void onError(String message);
}
