package com.app.amigo.Utils;

import com.app.amigo.Handler.AcceptRequestHandler;
import com.app.amigo.Handler.AdditionalInformationHandler;
import com.app.amigo.Handler.AddtoFavHandler;
import com.app.amigo.Handler.CreateRequestHandler;
import com.app.amigo.Handler.FacebookHandler;
import com.app.amigo.Handler.ForgotPasswordHandler;
import com.app.amigo.Handler.ForgotResetPasswordHandler;
import com.app.amigo.Handler.GetFavUserHandler;
import com.app.amigo.Handler.GetFriendListHandler;
import com.app.amigo.Handler.GetOldChatHandler;
import com.app.amigo.Handler.GetOldChatUsersListHandler;
import com.app.amigo.Handler.GetUserRequestHandler;
import com.app.amigo.Handler.GetUserlistHandler;
import com.app.amigo.Handler.GoogleHandler;
import com.app.amigo.Handler.LoginHandler;
import com.app.amigo.Handler.OTPHandler;
import com.app.amigo.Handler.PersonalProfileHandler;
import com.app.amigo.Handler.RegisterHandler;
import com.app.amigo.Handler.RejectRequestHandler;
import com.app.amigo.Handler.RemoveFromFavHandler;
import com.app.amigo.Handler.ResetPasswordHandler;
import com.app.amigo.Handler.UploadGalleryImagesHandler;
import com.app.amigo.Handler.UploadProfileImageHandler;
import com.app.amigo.Models.Additionalinformation.AditionalInformationExample;
import com.app.amigo.Models.Additionalinformation.UploadGalleryImages.UploadGalleryImagesExample;
import com.app.amigo.Models.Favourites.FavouritesExample;
import com.app.amigo.Models.Favourites.RemovefromFavs.RemoveFromFavExample;
import com.app.amigo.Models.Followers.AcceptRequest.AcceptRequestExample;
import com.app.amigo.Models.Followers.GetUserRequest.GetUserRequestExample;
import com.app.amigo.Models.Followers.RejectRequest.RejectRequestExample;
import com.app.amigo.Models.ForgotPassword.ForgotPasswordExample;
import com.app.amigo.Models.ForgotResetPassword.ForgotResetPasswordExample;
import com.app.amigo.Models.GetOldChat.GetOldChatExample;
import com.app.amigo.Models.Login.LoginExample;
import com.app.amigo.Models.Message.GetFriendList.GetFriendListExample;
import com.app.amigo.Models.Message.GetOldChatList.GetOldChatUsersListExample;
import com.app.amigo.Models.Otp.OtpExample;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileExample;
import com.app.amigo.Models.Profile.UploadProfileimageExample;
import com.app.amigo.Models.ResetPassword.ResetPasswordExample;
import com.app.amigo.Models.Signup.RegisterExample;
import com.app.amigo.Models.Trends.CreateRequest.CreateRequestExample;
import com.app.amigo.Models.Trends.Userlist.TrendsExample;
import com.example.AddtoFavExample;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServices {
    private static final String TAG = "WebServic es";
    //    public static final String BASE_URL = "http://93.188.167.68:4604/api/";
    public static final String BASE_URL = "http://3.136.56.91:8001/api/";
    //    public static final String ImageBaseURL = "http://13.54.226.124/";
    private static Retrofit retrofit = null;
    private static WebServices mInstance;
    private API api;
    private String accessToken;

    public WebServices() {
        mInstance = this;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(API.class);

        }
    }

    public static WebServices getmInstance() {
        return mInstance;
    }

    public void registermethod(String email, String password, final RegisterHandler registerHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        api.registerAPI(jsonObject).enqueue(new Callback<RegisterExample>() {
            @Override
            public void onResponse(Call<RegisterExample> call, Response<RegisterExample> response) {
                if (response.code() == 200) {
                    registerHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            registerHandler.onError(message);
                        } else {
                            registerHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterExample> call, Throwable t) {
                registerHandler.onError(t.getMessage());

            }
        });


    }


    public void loginMethod(String email, String password, final LoginHandler loginHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        api.loginAPI(jsonObject).enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, Response<LoginExample> response) {


                accessToken = response.headers().get("x-access-token");

//                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//                httpClient.addInterceptor(chain -> {
//
//                    Request original = chain.request();
//                    Request.Builder builder = original.newBuilder()
//                            .addHeader("x-access-token", accessToken)
//                            .method(original.method(), original.body());
//                    return chain.proceed(builder.build());
//
//                });

                if (response.code() == 200) {
                    loginHandler.onSuccess(response.body(), accessToken);
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            loginHandler.onError(message);
                        } else {
                            loginHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                loginHandler.onError(t.getMessage());
            }
        });
    }


    public void changePasswordMethod(String xaccesstoken, String id, String oldPassword, String newPassword, final ResetPasswordHandler resetPasswordHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("oldPassword", oldPassword);
        jsonObject.addProperty("newPassword", newPassword);
        api.resetpasswordAPI(xaccesstoken, id, jsonObject).enqueue(new Callback<ResetPasswordExample>() {
            @Override
            public void onResponse(Call<ResetPasswordExample> call, Response<ResetPasswordExample> response) {
                if (response.code() == 200) {
                    resetPasswordHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        resetPasswordHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResetPasswordExample> call, Throwable t) {
                resetPasswordHandler.onError(t.getMessage());

            }

        });


    }

    //ForgotPassword
    public void forgotpassword(String email, final ForgotPasswordHandler forgotPasswordHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        api.forgotPasswordAPI(jsonObject).enqueue(new Callback<ForgotPasswordExample>() {
            @Override
            public void onResponse(Call<ForgotPasswordExample> call, Response<ForgotPasswordExample> response) {
                if (response.code() == 200) {
                    forgotPasswordHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        forgotPasswordHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordExample> call, Throwable t) {
                forgotPasswordHandler.onError(t.getMessage());

            }
        });

    }
    //OtpVerification

    public void otpverifcation(String token, String otp, final OTPHandler otpHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("otp", otp);
        api.OtpAPI(token, jsonObject).enqueue(new Callback<OtpExample>() {
            @Override
            public void onResponse(Call<OtpExample> call, Response<OtpExample> response) {
                if (response.code() == 200) {
                    otpHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        otpHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<OtpExample> call, Throwable t) {
                otpHandler.onError(t.getMessage());

            }
        });

    }

    public void forgotResetPassword(String token, String newPassword, final ForgotResetPasswordHandler forgotResetPasswordHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("newPassword", newPassword);
        api.forgotresetpassAPI(token, jsonObject).enqueue(new Callback<ForgotResetPasswordExample>() {
            @Override
            public void onResponse(Call<ForgotResetPasswordExample> call, Response<ForgotResetPasswordExample> response) {
                if (response.code() == 200) {
                    forgotResetPasswordHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        forgotResetPasswordHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ForgotResetPasswordExample> call, Throwable t) {
                forgotResetPasswordHandler.onError(t.getMessage());

            }
        });

    }

    public void getUserlist(int pageNo, int pageSize, String token, final GetUserlistHandler getUserlistHandler) {
        api.getUserlistAPI(pageNo, pageSize, token).enqueue(new Callback<TrendsExample>() {
            @Override
            public void onResponse(Call<TrendsExample> call, Response<TrendsExample> response) {
                if (response.code() == 200) {
                    getUserlistHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        getUserlistHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<TrendsExample> call, Throwable t) {
                getUserlistHandler.onError(t.getMessage());

            }
        });

    }

    //Addtofav
    public void addtofav(String userId, String toLikeUserId, final AddtoFavHandler addtoFavHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("toLikeUserId", toLikeUserId);
        api.addtofavAPI(jsonObject).enqueue(new Callback<AddtoFavExample>() {
            @Override
            public void onResponse(Call<AddtoFavExample> call, Response<AddtoFavExample> response) {
                if (response.code() == 200) {
                    addtoFavHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        addtoFavHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<AddtoFavExample> call, Throwable t) {
                addtoFavHandler.onError(t.getMessage());

            }
        });

    }

    //GetFavouriteuser
    public void getfavuser(String id, final GetFavUserHandler getFavUserHandler) {
        api.getfavUserAPI(id).enqueue(new Callback<FavouritesExample>() {
            @Override
            public void onResponse(Call<FavouritesExample> call, Response<FavouritesExample> response) {
                if (response.code() == 200) {
                    getFavUserHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        getFavUserHandler.onError(message);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<FavouritesExample> call, Throwable t) {
                getFavUserHandler.onError(t.getMessage());
            }
        });
    }

    //Getuserdetail
    public void updatelist(String xaccesstoken, String id, String name, String email, String description, String password, String phoneNo, String dob, String school, String aboutMe, String livingIn, String height, String weight, String favSports, String degreeOfEduction, String lookingFor, String sexualOrientation, String myWork, String sex, String status, String loc, String type, String coordinates, final AdditionalInformationHandler additionalInformationHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("description", description);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("phoneNo", phoneNo);
        jsonObject.addProperty("dob", dob);
        jsonObject.addProperty("school", school);
        jsonObject.addProperty("aboutMe", aboutMe);
        jsonObject.addProperty("livingIn", livingIn);
        jsonObject.addProperty("height", height);
        jsonObject.addProperty("weight", weight);
        jsonObject.addProperty("favSports", favSports);
        jsonObject.addProperty("degreeOfEduction", degreeOfEduction);
        jsonObject.addProperty("lookingFor", lookingFor);
        jsonObject.addProperty("sexualOrientation", sexualOrientation);
        jsonObject.addProperty("myWork", myWork);
        jsonObject.addProperty("sex", sex);
        jsonObject.addProperty("status", status);
        jsonObject.addProperty("loc", loc);
//        jsonObject.addProperty("loc", ArrayList<String>[]);
        jsonObject.addProperty("type", type);
        jsonObject.addProperty("coordinates", coordinates);
        api.updatelist(xaccesstoken, id, jsonObject).enqueue(new Callback<AditionalInformationExample>() {
            @Override
            public void onResponse(Call<AditionalInformationExample> call, Response<AditionalInformationExample> response) {
                if (response.code() == 200) {
                    additionalInformationHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            additionalInformationHandler.onError(message);
                        } else {
                            additionalInformationHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }


            @Override
            public void onFailure(Call<AditionalInformationExample> call, Throwable t) {
                additionalInformationHandler.onError(t.getMessage());


            }
        });

    }

    //GetpersonalprofileData
    public void getpersonalprofile(String id, final PersonalProfileHandler personalProfileHandler) {
        api.getpersonalProfileDataAPI(id).enqueue(new Callback<PersonalProfileExample>() {
            @Override
            public void onResponse(Call<PersonalProfileExample> call, Response<PersonalProfileExample> response) {
                if (response.code() == 200) {
                    personalProfileHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            personalProfileHandler.onError(message);
                        } else {
                            personalProfileHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<PersonalProfileExample> call, Throwable t) {
                personalProfileHandler.onError(t.getMessage());

            }
        });

    }

    //RemoveUserfromfav
    public void removeFromFav(String userId, String toUnLikeUserId, final RemoveFromFavHandler removeFromFavHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userId", userId);
        jsonObject.addProperty("toUnLikeUserId", toUnLikeUserId);
        api.removefromfavAPI(jsonObject).enqueue(new Callback<RemoveFromFavExample>() {
            @Override
            public void onResponse(Call<RemoveFromFavExample> call, Response<RemoveFromFavExample> response) {
                if (response.code() == 200) {
                    removeFromFavHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {

                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            removeFromFavHandler.onError(message);
                        } else {
                            removeFromFavHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RemoveFromFavExample> call, Throwable t) {
                removeFromFavHandler.onError(t.getMessage());

            }
        });

    }


    //CreateRequestAPI
    public void createRequest(String token, String reqTo, String reqBy, final CreateRequestHandler createRequestHandler) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("reqTo", reqTo);
        jsonObject.addProperty("reqBy", reqBy);
        api.createRequestAPI(token, jsonObject).enqueue(new Callback<CreateRequestExample>() {
            @Override
            public void onResponse(Call<CreateRequestExample> call, Response<CreateRequestExample> response) {
                if (response.code() == 200) {
                    createRequestHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            createRequestHandler.onError(message);
                        } else {
                            createRequestHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CreateRequestExample> call, Throwable t) {
                createRequestHandler.onError(t.getMessage());


            }
        });

    }

    //GetuserRequest

    public void getuserrequest(String token, String id, final GetUserRequestHandler getUserRequestHandler) {
        api.getuserrequestAPI(token, id).enqueue(new Callback<GetUserRequestExample>() {
            @Override
            public void onResponse(Call<GetUserRequestExample> call, Response<GetUserRequestExample> response) {
                if (response.code() == 200) {
                    getUserRequestHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            getUserRequestHandler.onError(message);
                        } else {
                            getUserRequestHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserRequestExample> call, Throwable t) {
                getUserRequestHandler.onError(t.getMessage());

            }
        });

    }

    //RejectRequest
    public void rejectrequest(String token, String id, final RejectRequestHandler rejectRequestHandler) {
        api.rejectrequestAPI(token, id).enqueue(new Callback<RejectRequestExample>() {
            @Override
            public void onResponse(Call<RejectRequestExample> call, Response<RejectRequestExample> response) {
                if (response.code() == 200) {
                    rejectRequestHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            rejectRequestHandler.onError(message);
                        } else {
                            rejectRequestHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RejectRequestExample> call, Throwable t) {
                rejectRequestHandler.onError(t.getMessage());

            }
        });
    }

    //acceptrequest
    public void acceptrequest(String token, String id, final AcceptRequestHandler acceptRequestHandler) {
        api.appectrequestAPI(token, id).enqueue(new Callback<AcceptRequestExample>() {
            @Override
            public void onResponse(Call<AcceptRequestExample> call, Response<AcceptRequestExample> response) {
                if (response.code() == 200) {
                    acceptRequestHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            acceptRequestHandler.onError(message);
                        } else {
                            acceptRequestHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<AcceptRequestExample> call, Throwable t) {
                acceptRequestHandler.onError(t.getMessage());

            }
        });
    }

    //UploadProfileimage
    public void uploadprofileimage(MultipartBody.Part image, String id, final UploadProfileImageHandler uploadProfileImageHandler) {
        api.updateProfileAPI(image, id).enqueue(new Callback<UploadProfileimageExample>() {
            @Override
            public void onResponse(Call<UploadProfileimageExample> call, Response<UploadProfileimageExample> response) {

                if (response.code() == 200) {
                    uploadProfileImageHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            uploadProfileImageHandler.onError(message);
                        } else {
                            uploadProfileImageHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<UploadProfileimageExample> call, Throwable t) {
                uploadProfileImageHandler.onError(t.getMessage());

            }
        });


    }

    //uploadGalleryImages
    public void uploadgalleryImages(MultipartBody.Part images, String id, final UploadGalleryImagesHandler uploadGalleryImagesHandler) {
        api.uploadgalleryimagesAPI(images, id).enqueue(new Callback<UploadGalleryImagesExample>() {
            @Override
            public void onResponse(Call<UploadGalleryImagesExample> call, Response<UploadGalleryImagesExample> response) {
                if (response.code() == 200) {
                    uploadGalleryImagesHandler.onSuccess(response.body());
                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            uploadGalleryImagesHandler.onError(message);
                        } else {
                            uploadGalleryImagesHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadGalleryImagesExample> call, Throwable t) {
                uploadGalleryImagesHandler.onError(t.getMessage());

            }
        });
    }

    //getFriendlist
    public void getFriendList(String token, String id, final GetFriendListHandler getFriendListHandler) {
        api.getfriendlist(token, id).enqueue(new Callback<GetFriendListExample>() {
            @Override
            public void onResponse(Call<GetFriendListExample> call, Response<GetFriendListExample> response) {
                if (response.code() == 200) {
                    getFriendListHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            getFriendListHandler.onError(message);
                        } else {
                            getFriendListHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetFriendListExample> call, Throwable t) {
                getFriendListHandler.onError(t.getMessage());

            }
        });
    }


    //getOldChatconversationsUserList
    public void getOldChatconversationUserList(String id, final GetOldChatUsersListHandler getOldChatUsersListHandler) {
//        String id = CSPreferences.readString()
        api.getOldChatUsersList(id).enqueue(new Callback<GetOldChatUsersListExample>() {
            @Override
            public void onResponse(Call<GetOldChatUsersListExample> call, Response<GetOldChatUsersListExample> response) {
                if (response.code() == 200) {
                    getOldChatUsersListHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            getOldChatUsersListHandler.onError(message);
                        } else {
                            getOldChatUsersListHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<GetOldChatUsersListExample> call, Throwable t) {
                getOldChatUsersListHandler.onError(t.getMessage());

            }
        });
    }

    //getoldchatconversations
    public void getOldChatconversations(String conversationId, int pageNo, int pageSize, final GetOldChatHandler getOldChatHandler) {
        api.getOldChat(conversationId, pageNo, pageSize).enqueue(new Callback<GetOldChatExample>() {
            @Override
            public void onResponse(Call<GetOldChatExample> call, Response<GetOldChatExample> response) {
                if (response.code() == 200) {
                    getOldChatHandler.onSuccess(response.body());

                } else {
                    String responceData = SocketConnection.convertStreamToString(response.errorBody().byteStream());
                    try {
                        JSONObject jsonObject = new JSONObject(responceData);
                        String message = jsonObject.optString("message");
                        String error = jsonObject.optString("error");
                        if (!(message.equalsIgnoreCase(""))) {
                            getOldChatHandler.onError(message);
                        } else {
                            getOldChatHandler.onError(error);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetOldChatExample> call, Throwable t) {
                getOldChatHandler.onError(t.getMessage());

            }
        });
    }


    //socialLink
    public void facebookMethod(String token, final FacebookHandler facebookHandler) {
        api.facebookApi().enqueue(new Callback<GetFriendListExample>() {
            @Override
            public void onResponse(Call<GetFriendListExample> call, Response<GetFriendListExample> response) {
            }

            @Override
            public void onFailure(Call<GetFriendListExample> call, Throwable t) {

            }
        });

    }


    public void google(String token, final GoogleHandler googleHandler) {
        api.googleApi().enqueue(new Callback<GetFriendListExample>() {
            @Override
            public void onResponse(Call<GetFriendListExample> call, Response<GetFriendListExample> response) {
            }

            @Override
            public void onFailure(Call<GetFriendListExample> call, Throwable t) {

            }
        });

    }


}
