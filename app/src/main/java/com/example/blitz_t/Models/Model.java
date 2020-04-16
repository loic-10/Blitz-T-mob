package com.example.blitz_t.Models;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Model {

    public String generateCode(String initial, int value, int size, char car){
        StringBuilder code = new StringBuilder(initial);
        for(int i = 0; i < size - Integer.toString(value).length(); i++){
            code.append(car);
        }
        return code.append(value).toString();
    }

    public static Date ConvertToDate(String dateInString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy" , Locale.FRANCE);
        return formatter.parse(dateInString);
    }

    public static String ConvertToString(String dateInString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);
        Date date = formatter.parse(dateInString);
        assert date != null;
        return formatter.format(date);
    }


    public static void saveFormPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        String contentPreference = new Gson().toJson(object);
        SharedPreferences.Editor editor = contextWrapper.getSharedPreferences(preferenceFileKey , Context.MODE_PRIVATE).edit();
        editor.putString(filePreference , contentPreference);
        editor.apply();
    }

    public static void clearFormPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        String contentPreference = new Gson().toJson(object);
        SharedPreferences.Editor editor = contextWrapper.getSharedPreferences(preferenceFileKey , Context.MODE_PRIVATE).edit();
        editor.putString(filePreference , contentPreference);
        editor.apply();
        editor.clear();
    }

    public static Object contentPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        SharedPreferences sharedPref = contextWrapper.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        String member_string = sharedPref.getString(filePreference, null);
        if(member_string != null) {
            return new Gson().fromJson(member_string, object.getClass());
        }
        return null;
    }

    public static String currentDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(c.getTime());
    }

//    public static boolean isOnline(Context context) {
//        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        assert cm != null;
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }

}
