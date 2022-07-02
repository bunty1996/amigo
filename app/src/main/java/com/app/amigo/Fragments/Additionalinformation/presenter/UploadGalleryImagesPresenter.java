package com.app.amigo.Fragments.Additionalinformation.presenter;

import android.app.Activity;
import android.graphics.Bitmap;

import com.app.amigo.Fragments.Additionalinformation.view.AditionalInformationView;
import com.app.amigo.Handler.UploadGalleryImagesHandler;
import com.app.amigo.Models.Additionalinformation.UploadGalleryImages.UploadGalleryImagesExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadGalleryImagesPresenter {
    private Activity activity;
    private AditionalInformationView aditionalInformationView;

    private RequestBody id;
    private MultipartBody.Part imagePart;
//    private MultipartBody.Part[] imagePart = new MultipartBody.Part[10];
    private RequestBody requestFile11;
    private RequestBody requestFile;
    private Map<String, MultipartBody.Part> list;


    public UploadGalleryImagesPresenter(Activity activity, AditionalInformationView aditionalInformationView) {
        this.activity = activity;
        this.aditionalInformationView = aditionalInformationView;

    }

    public void UploadGalleryIMages(Bitmap photo) {
        /*if (paths != null) {

//            List<MultipartBody.Part> list = new ArrayList<>();
//            int i = 0;
//            for (Uri uri : paths) {
////                String fileName = FileUtils.getFile(activity, uri).getName();
//                //very important files[]
//                MultipartBody.Part imageRequest = prepareFilePart("file[]", uri);
//                list.add(imageRequest);
//            }

            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            builder.addFormDataPart("user_name", "Robert");
            builder.addFormDataPart("email", "mobile.apps.pro.vn@gmail.com");

            // Map is used to multipart the file using okhttp3.RequestBody
            // Multiple Images
            for (int i = 0; i < paths.size(); i++) {
                File file = new File(String.valueOf(paths.get(i)));
                builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
            }

            MultipartBody requestBody = builder.build();
            //creating request body for file
//             list = new HashMap<>();
//            for (Uri uri : photo) {
//                imageRequest = prepareFilePart("file[]", uri);
//                list.put("images", imageRequest);
//            }


//            for (int index = 0; index < 5; index++) {
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
////                photo.compress(Bitmap.CompressFormat.JPEG, 40, stream);
//                byte[] photoByteArray = stream.toByteArray();
//                 requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), photoByteArray);
//                id = RequestBody.create(MediaType.parse("multipart/form-data"), CSPreferences.readString(activity, Utils.USERID));
//                Random random = new Random();
//                imagePart[index] = MultipartBody.Part.createFormData("file",
//                        "abc" + random.nextInt(1000000) + ".jpg", requestFile11);
//            }
*/

        if (photo != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 40, stream);
            byte[] photoByteArray = stream.toByteArray();
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), photoByteArray);
            id = RequestBody.create(MediaType.parse("multipart/form-data"), CSPreferences.readString(activity, Utils.USERID));
            Random random = new Random();
            imagePart = MultipartBody.Part.createFormData("file", "abc" + random.nextInt(1000000) + ".jpg", requestFile);
        }
            if (Utils.isNetworkConnected(activity)) {
//                aditionalInformationView.showDialog(activity);
                String id = CSPreferences.readString(activity, Utils.USERID);
                WebServices.getmInstance().uploadgalleryImages(imagePart, id, new UploadGalleryImagesHandler() {
                    @Override
                    public void onSuccess(UploadGalleryImagesExample uploadGalleryImagesExample) {
//                        aditionalInformationView.hideDialog();
                        if (uploadGalleryImagesExample != null) {
//                            aditionalInformationView.showMessage(activity, uploadGalleryImagesExample.getData());

                        }

                    }

                    @Override
                    public void onError(String message) {
//                        aditionalInformationView.hideDialog();
//                        aditionalInformationView.showMessage(activity, message);

                    }
                });
            }
        }
}


