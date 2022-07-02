package com.app.amigo;

import android.app.Activity;
import android.content.Intent;

import com.app.amigo.Fragments.Personalinformation.PersonalinformationFragment;
import com.app.amigo.Utils.Utils;

public class Utility {
    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_WHITE = 1;
    public final static int THEME_BLUE = 2;

    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
//        activity.finish();
//        activity.startActivity(new Intent(activity, activity.getClass()));
//        Intent intent = new Intent(activity,MainActivity2.class);
//        activity.startActivity(intent);
        Utils.changeFragment(activity, new PersonalinformationFragment());

    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.Maletheme);
                break;
            case THEME_WHITE:
                activity.setTheme(R.style.Femaletheme);
                break;
            case THEME_BLUE:
//                activity.setTheme(R.style.Thirdheme);
                break;
        }
    }
}
