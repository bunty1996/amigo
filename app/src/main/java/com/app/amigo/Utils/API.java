package com.app.amigo.Utils;

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

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    //////users////////

    //    @FormUrlEncoded
//    @POST("users/login")
//    Call<LoginExample> loginAPI(@Field("email") String email, @Field("password") String password);
//
    @POST("users/create")
    Call<RegisterExample> registerAPI(@Body JsonObject jsonObject);

    @POST("users/login")
    Call<LoginExample> loginAPI(@Body JsonObject jsonObject);

    //Updatelist
    @PUT("users/update/{id}")
    Call<AditionalInformationExample> updatelist(@Header("x-access-token") String xaccesstoken, @Path("id") String id, @Body JsonObject jsonObject);

    @PUT("users/changePassword/{id}")
    Call<ResetPasswordExample> resetpasswordAPI(@Header("x-access-token") String token, @Path("id") String id, @Body JsonObject jsonObject);

    //AddtoFav
    @POST("favourites/add")
    Call<AddtoFavExample> addtofavAPI(@Body JsonObject jsonObject);

    //userlist
    @GET("users/matchList")
    Call<TrendsExample> getUserlistAPI(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Header("x-access-token") String token);

    //getpersonalprofileData
    @GET("users/currentUser/{id}")
    Call<PersonalProfileExample> getpersonalProfileDataAPI(@Path("id") String id);

    //Getfavourites/ByUserId/{id}
    @GET("favourites/ByUserId/{id}")
    Call<FavouritesExample> getfavUserAPI(@Path("id") String id);

    //ForgotPasswordAPI
    @POST("users/forgotPassword")
    Call<ForgotPasswordExample> forgotPasswordAPI(@Body JsonObject jsonObject);


    //OtpVerification
    @POST("users/otpVerify")
    Call<OtpExample> OtpAPI(@Header("x-access-token") String token, @Body JsonObject jsonObject);

    //ForgotResetPassword
    @POST("users/changePassword")
    Call<ForgotResetPasswordExample> forgotresetpassAPI(@Header("x-access-token") String token, @Body JsonObject jsonObject);

    //RemovefromFavourites
    @POST("favourites/remove")
    Call<RemoveFromFavExample> removefromfavAPI(@Body JsonObject jsonObject);


    //CreateRequest
    @POST("requests/create")
    Call<CreateRequestExample> createRequestAPI(@Header("x-access-token") String token, @Body JsonObject jsonObject);

    //GetuserRequest
    @GET("requests/listByUserId/{id}")
    Call<GetUserRequestExample> getuserrequestAPI(@Header("x-access-token") String token, @Path("id") String id);

    //RejectRequest
    @PUT("requests/reject/{id}")
    Call<RejectRequestExample> rejectrequestAPI(@Header("x-access-token") String token, @Path("id") String id);


    //AcceptRequest
    @PUT("requests/accept/{id}")
    Call<AcceptRequestExample> appectrequestAPI(@Header("x-access-token") String token, @Path("id") String id);


    //ProfileImage
    @Multipart
    @PUT("users/profileImageUpload/{id}")
    Call<UploadProfileimageExample> updateProfileAPI(@Part MultipartBody.Part image, @Path("id") String id);


    //UploaddGalleryImages
    @Multipart
    @PUT("users/uploadImagesByUserId/{id}")
    Call<UploadGalleryImagesExample> uploadgalleryimagesAPI(@Part MultipartBody.Part image, @Path("id") String id);

//getFriendList

    @GET("users/myFriend/{id}")
    Call<GetFriendListExample> getfriendlist(@Header("x-access-token") String token, @Path("id") String id);


    //getOldChatConversationUserList
    @GET("conversations/conversationList/{id}")
    Call<GetOldChatUsersListExample> getOldChatUsersList(@Path("id") String id);


    //getOLdChatConversations
    @GET("conversations/getOldCoversations")
    Call<GetOldChatExample> getOldChat(@Query("conversationId") String conversationId, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    //socialLinkAPI
    @POST("hjsdkjds")
    Call<GetFriendListExample> facebookApi();

    @POST("kjzxkj")
    Call<GetFriendListExample>googleApi();
}