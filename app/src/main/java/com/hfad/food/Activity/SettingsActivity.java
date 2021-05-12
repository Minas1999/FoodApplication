package com.hfad.food.Activity;

import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.hfad.food.R;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        Load_setting();
    }



    private void Load_setting() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean chk_night = sp.getBoolean("finger", false);

        if (chk_night){
//            getListView().setBackgroundColor(Color.parseColor("#222222"));
            Toast.makeText(this, "okk", Toast.LENGTH_SHORT).show();
        }
        else{
            //getListView().setBackgroundColor(Color.parseColor("#ffffff"));
            Toast.makeText(this, "Cokk", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Load_setting();
    }
}
