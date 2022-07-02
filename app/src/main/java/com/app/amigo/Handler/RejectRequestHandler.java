package com.app.amigo.Handler;

import com.app.amigo.Models.Favourites.RemovefromFavs.RemoveFromFavExample;
import com.app.amigo.Models.Followers.RejectRequest.RejectRequestExample;

public interface RejectRequestHandler {
    public void onSuccess(RejectRequestExample rejectRequestExample);
    public void onError(String message);
}
