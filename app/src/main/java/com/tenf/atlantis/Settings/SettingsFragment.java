package com.tenf.atlantis.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;

import com.tenf.atlantis.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
    public SharedPreferences.OnSharedPreferenceChangeListener prefListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);



    }

    @Override
    public void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("pref_notif_boodschappen")) {
            if(sharedPreferences.getBoolean(key, true)) {
                FirebaseMessaging.getInstance().subscribeToTopic("boodschappen");
            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("boodschappen");
            }
        }
        if(key.equals("pref_notif_vuilnis")) {
            if(sharedPreferences.getBoolean(key, true)) {
                FirebaseMessaging.getInstance().subscribeToTopic("vuilnis");
            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("vuilnis");
            }
        }
        setTaakjes(sharedPreferences, key);
        if(key.equals("pref_user_names")) {
            if(sharedPreferences.getBoolean("pref_notif_taakjes", true)) {
                String[] names = getResources().getStringArray(R.array.pref_user_names_values);
                List<String> list = new ArrayList<String>(Arrays.asList(names));

                list.remove(sharedPreferences.getString("pref_user_names", ""));
                for (String name : list) {
                    String topic = name + "_taakjes";
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                }
                String topic = sharedPreferences.getString("pref_user_names", "") + "_taakjes";
                FirebaseMessaging.getInstance().subscribeToTopic(topic);
            }
        }

    }
    public void setTaakjes(SharedPreferences sharedPreferences, String key) {
        if (!sharedPreferences.getString("pref_user_names", "").equals("0")&& !sharedPreferences.getString("pref_user_names", "").equals("")) {
            String topic = sharedPreferences.getString("pref_user_names", "") + "_taakjes";
            if (key.equals("pref_notif_taakjes")) {
                if (sharedPreferences.getBoolean(key, true)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(topic);
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                }
            }
        }
    }
}

