package com.app.amigo.Handler;

import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileExample;

public interface PersonalProfileHandler {
    public void onSuccess(PersonalProfileExample personalProfileExample);

    public void onError(String message);
}
