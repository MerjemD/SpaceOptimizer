package com.example.project2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            // User je već logovan
            startActivity(new Intent(this, HomeActivity.class));

        } else {

            // Nije logovan
            startActivity(new Intent(this, MainActivity.class));
        }

        finish();
    }
}