package catalin.coinnews;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.UUID;

import catalin.coinnews.MainActivity;

/**
 * Created by catalin on 02/12/17.
 */

public class Util {

    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) MainActivity.getContextOfApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

//    public static void setPhoneId() {
//        SharedPreferences settings = MainActivity.getContextOfApplication().getSharedPreferences(MainActivity.PREFS_NAME, 0);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.putString("phoneId", generatePhoneId());
//        editor.apply();
//    }
//
//    public static String getPhoneId() {
//        SharedPreferences settings = MainActivity.getContextOfApplication().getSharedPreferences(MainActivity.PREFS_NAME, 0);
//        String phoneId = settings.getString("phoneId", "");
//        return phoneId;
//    }

//    public static String generatePhoneId() {
//        return UUID.randomUUID().toString();
//    }

}
