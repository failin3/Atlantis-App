package com.tenf.atlantis.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.google.firebase.messaging.FirebaseMessaging;

import com.tenf.atlantis.R;



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
        if(key.equals("pref_notif_taakjes")) {
            if(sharedPreferences.getBoolean(key, true)) {
                FirebaseMessaging.getInstance().subscribeToTopic("taakjes");
            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("taakjes");
            }
        }

    }
}

