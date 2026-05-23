package com.example.project2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    Switch darkModeSwitch;
    Switch notificationSwitch;

    Spinner languageSpinner;

    Button saveSettingsBtn;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        darkModeSwitch =
                findViewById(R.id.darkModeSwitch);

        notificationSwitch =
                findViewById(R.id.notificationSwitch);

        languageSpinner =
                findViewById(R.id.languageSpinner);

        saveSettingsBtn =
                findViewById(R.id.saveSettingsBtn);

        preferences =
                getSharedPreferences(
                        "settings",
                        MODE_PRIVATE
                );

        // LANGUAGE LIST

        String[] languages = {
                "Bosanski",
                "English",
                "Deutsch"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        languages
                );

        languageSpinner.setAdapter(adapter);

        // LOAD SAVED SETTINGS

        boolean darkMode =
                preferences.getBoolean(
                        "darkMode",
                        false
                );

        boolean notifications =
                preferences.getBoolean(
                        "notifications",
                        true
                );

        int languagePosition =
                preferences.getInt(
                        "language",
                        0
                );

        darkModeSwitch.setChecked(darkMode);
        notificationSwitch.setChecked(notifications);
        languageSpinner.setSelection(languagePosition);

        // APPLY DARK MODE

        if (darkMode) {

            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
            );

        } else {

            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
            );
        }

        // SAVE SETTINGS

        saveSettingsBtn.setOnClickListener(v -> {

            SharedPreferences.Editor editor =
                    preferences.edit();

            editor.putBoolean(
                    "darkMode",
                    darkModeSwitch.isChecked()
            );

            editor.putBoolean(
                    "notifications",
                    notificationSwitch.isChecked()
            );

            editor.putInt(
                    "language",
                    languageSpinner.getSelectedItemPosition()
            );

            editor.apply();

            // APPLY DARK MODE AGAIN

            if (darkModeSwitch.isChecked()) {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                );

            } else {

                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                );
            }

            Toast.makeText(
                    SettingsActivity.this,
                    "Settings saved!",
                    Toast.LENGTH_SHORT
            ).show();

            recreate();

        });

    }
}