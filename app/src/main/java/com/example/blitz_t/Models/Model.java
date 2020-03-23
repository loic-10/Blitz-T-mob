package com.example.blitz_t.Models;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.R;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
        editor.commit();
    }

    public static Object contentPreference ( Object object, String filePreference, String preferenceFileKey, ContextWrapper contextWrapper ) {
        SharedPreferences sharedPref = contextWrapper.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        String member_string = sharedPref.getString(filePreference, null);
        if(member_string != null) {
            return new Gson().fromJson(member_string, object.getClass());
        }
        return null;
    }

}
