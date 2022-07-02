package com.app.amigo.Fragments.LoginFragment;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.amigo.Activities.Home.HomeActivity;
import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Fragments.ForgotPassword.ForgotPasswordFragment;
import com.app.amigo.Fragments.LoginFragment.Presenter.LoginPresenter;
import com.app.amigo.Fragments.LoginFragment.View.LoginView;
import com.app.amigo.Fragments.Signup.SignupFragment;
import com.app.amigo.Models.Login.LoginData;
import com.app.amigo.R;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class LoginFragment extends Fragment implements View.OnClickListener, LoginView, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Activity activity;
    private View view;
    private Button btn_loginAccount;
    private TextView txt_signUp;
    private TextView txt_forgotPassword;
    private TextView tv_google;
    private TextView tv_facebook;
    private ImageView img_eye;
    private EditText et_SignupName;
    private EditText et_Password;

    Boolean flag = true;
    //    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    LoginPresenter loginPresenter;
    LoginView loginView;


    private GoogleSignInOptions gso;
    private int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager callbackManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        activity = getActivity();
        init();
        listeners();
//        tv_facebook.setReadPermissions(Arrays.asList(EMAIL));
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken = AccessToken.getCurrentAccessToken();
                        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                        CSPreferences.putString(activity, Utils.FACEBOOKTOKEN, loginResult.getAccessToken().getToken());
                        Log.d("TOEKNVALUEFB", loginResult.getAccessToken().getToken());


                        CSPreferences.putString(activity, Utils.USERLOGIN, "true");


                        // App code
                        Intent intent = new Intent(activity, SelectGenderActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(activity, "Cancled", Toast.LENGTH_SHORT).show();
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show();
                        Toast.makeText(activity, exception.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("facebookcheck", exception.toString());

                    }
                });


        loginPresenter = new LoginPresenter(activity, this);


        return view;
    }

    private void init() {
//        callbackManager = CallbackManager.Factory.create();
        btn_loginAccount = view.findViewById(R.id.btn_loginAccount);
        txt_signUp = view.findViewById(R.id.txt_signUp);
        txt_forgotPassword = view.findViewById(R.id.txt_forgotPassword);
        tv_google = view.findViewById(R.id.tv_google);
        tv_facebook = view.findViewById(R.id.tv_facebook);
        img_eye = view.findViewById(R.id.img_eye);
        et_SignupName = view.findViewById(R.id.et_SignupName);
        et_Password = view.findViewById(R.id.et_Password);
    }

    private void listeners() {
        btn_loginAccount.setOnClickListener(this);
        txt_signUp.setOnClickListener(this);
        txt_forgotPassword.setOnClickListener(this);
        tv_google.setOnClickListener(this);
        tv_facebook.setOnClickListener(this);
        img_eye.setOnClickListener(this);

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
//
////        updateUI(account);//        updateUI(account);
//    }

    @Override
    public void onClick(View v) {

        if (v == btn_loginAccount) {
            loginPresenter.loginMethod(et_SignupName, et_Password);

        } else if (v == txt_signUp) {
            SignupFragment fragment = new SignupFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContainer, fragment);
            transaction.commit();
        } else if (v == txt_forgotPassword) {
            ForgotPasswordFragment fragment = new ForgotPasswordFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContainer, fragment);
            transaction.commit();
        } else if (v == tv_google) {
            signIn();
        } else if (v == tv_facebook) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//            facebookLogin();
        } else if (v == img_eye) {
            if (flag) {
                img_eye.setImageResource(R.drawable.eyes2);
                if (et_Password.getTransformationMethod().getClass().getSimpleName().equals("PasswordTransformationMethod")) {
                    et_Password.setTransformationMethod(new SingleLineTransformationMethod());
                } else {
                    et_Password.setTransformationMethod(new PasswordTransformationMethod());
                }

                et_Password.setSelection(et_Password.getText().length());

            } else {
                img_eye.setImageResource(R.drawable.eyes);
                et_Password.setTransformationMethod(new PasswordTransformationMethod());

            }
            flag = !flag;

        }
    }


//    private void facebookLogin() {
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        GraphRequest request = GraphRequest.newMeRequest(
//                                loginResult.getAccessToken(),
//                                new GraphRequest.GraphJSONObjectCallback() {
//                                    @Override
//
//                                    public void onCompleted(
//                                            JSONObject object,
//
//                                            GraphResponse response) {
//                                        try {
//
//
//                                            String name = object.getString("name");
//                                            String email = object.getString("email");
//                                            String id = object.getString("id");
//                                            String token = object.getString("token");
////                                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
////                                            Toast.makeText(activity, "User Email: " + email, Toast.LENGTH_SHORT).show();
////                                            Intent i = new Intent(activity, HomeActivity.class);
////                                            startActivity(i);
////                                            ((Activity) getActivity()).overridePendingTransition(0, 0);
////                                            loginPresenter.socialLink(id, "F", email, name, "", "9875654636", "");
////
////                                    login_signUpPresenter_Login.fbsocialLogin(name, email, id, "", "F", "");
//                                            //  Toast.makeText(activity,object.toString() , Toast.LENGTH_LONG).show();
//                                            Log.e("facebook_data", object.toString());
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                });
//                        Bundle parameters = new Bundle();
//                        parameters.putString("fields", "id,name,link,birthday,gender,email");
//                        request.setParameters(parameters);
//                        request.executeAsync();
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Log.e("onCancel", "onCancel");
//                    }
//
//                    @Override
//                    public void onError(FacebookException error) {
//                        Log.e("error", error.toString());
//
//                    }
//                });
//    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);


        }
    }

        private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
            try {
                GoogleSignInAccount account = completedTask.getResult(ApiException.class);
                Intent intent = new Intent(activity, SelectGenderActivity.class);
                startActivity(intent);
                // Signed in successfully, show authenticated UI.

            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                Toast.makeText(activity, "failed", Toast.LENGTH_SHORT).show();
            }
        }


    private void gotoProfile() {
        Intent intent = new Intent(activity, SelectGenderActivity.class);
        startActivity(intent);
    }
   /* private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            if (account != null) {
                String personName = account.getDisplayName();
                String personGivenName = account.getGivenName();
                String personFamilyName = account.getFamilyName();
                String personEmail = account.getEmail();
                String personId = account.getId();
                Uri personPhoto = account.getPhotoUrl();
                Log.e("name", personName);


            }

            CSPreferences.putString(activity,Utils.USERLOGIN,"true");
            Intent i = new Intent(getActivity(), HomeActivity.class);
            startActivity(i);

            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }*/


    @Override
    public void showMessage(Activity activity, String msg) {

        Utils.showMessage(activity, msg);
    }

    @Override
    public void showDialog(Activity activity) {

        Utils.showDialogMethod(activity, activity.getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }

    @Override
    public void setData(Activity activity, LoginData data) {
        if (data!=null){
            CSPreferences.putString(activity,Utils.GENDERSELECT,data.getSex());
        }


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {


    }
}