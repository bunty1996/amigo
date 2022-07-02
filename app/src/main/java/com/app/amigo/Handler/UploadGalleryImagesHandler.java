package com.app.amigo.Handler;

import com.app.amigo.Models.Additionalinformation.UploadGalleryImages.UploadGalleryImagesExample;
import com.app.amigo.Models.Profile.UploadProfileimageExample;

public interface UploadGalleryImagesHandler {
    public void onSuccess(UploadGalleryImagesExample uploadGalleryImagesExample);
    public void onError(String message);
}
