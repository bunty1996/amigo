package com.app.amigo.Handler;

import com.app.amigo.Models.Trends.CreateRequest.CreateRequestExample;
import com.example.AddtoFavExample;

public interface CreateRequestHandler {
    public void onSuccess(CreateRequestExample createRequestExample);
    public void onError(String message);
}
