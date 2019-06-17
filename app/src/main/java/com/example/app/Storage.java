package com.example.app;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Storage {

    private final SharedPreferences preferences;
    private final String MY_PREFS_NAME = "mypref";
    private final String PREF_DATA = "mydata";

    public Storage(Context context) {
        preferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    }

    public void SaveVotes(ArrayList<ListModel> mydata) {
        SharedPreferences.Editor editor = preferences.edit();
        JSONArray array = new JSONArray();

        for(int i=0;i<mydata.size();i++) {
            try {
                array.put(mydata.get(i).toJSONObject());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        editor.putString(PREF_DATA, array.toString());
        editor.apply();
    }

    public void LoadVotes(ArrayList<ListModel> mydata) {
        mydata.clear();
        try {
            JSONArray array = new JSONArray(preferences.getString(PREF_DATA, "[]"));
            for(int i=0;i<array.length();i++) {
                mydata.add(new ListModel(array.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
