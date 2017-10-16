package gvn.project311.coffeeS.Example.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import gvn.project311.coffeeS.Example.Application.coffeeSApplication;

/**
 * Created by admin on 10/10/17.
 */

public class AndroidSharedPreferences {

    private static AndroidSharedPreferences instance;

    public static AndroidSharedPreferences getInstance() {
        if(instance == null)
        {
            instance = new AndroidSharedPreferences(coffeeSApplication.getContext());
        }
        return instance;
    }

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private AndroidSharedPreferences(Context context) {
        if(context == null)
        {
            Log.i("app","Error AndroidSharedPreferences context null");
        }
        else
        {
            sharedPreferences = context.getSharedPreferences("coffeeS", Context.MODE_PRIVATE);
        }
    }

    public boolean StoringDataString(String key, String value)
    {
        editor = sharedPreferences.edit();
        editor.putString(key,value);
        return editor.commit();
    }

    public String RetrievingDataString(String key)
    {
        return sharedPreferences.getString(key,null);
    }

    public boolean StoringDataBoolean(String key, Boolean bool)
    {
        editor = sharedPreferences.edit();
        editor.putBoolean(key,bool);
        return editor.commit();
    }

    public boolean RetrievingDataBoolean(String key)
    {
        return sharedPreferences.getBoolean(key,true);
    }

}
