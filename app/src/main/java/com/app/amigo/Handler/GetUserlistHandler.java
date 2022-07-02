package com.app.amigo.Handler;

import com.app.amigo.Models.Trends.Userlist.TrendsExample;

public interface GetUserlistHandler {
    public void onSuccess(TrendsExample userlistExample);
    public void onError(String message);
}
