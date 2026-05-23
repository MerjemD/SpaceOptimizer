package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    TextView nameText, emailText;
    Button changePasswordBtn, logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);

        changePasswordBtn =
                findViewById(R.id.changePasswordBtn);

        logoutBtn =
                findViewById(R.id.logoutBtn);

        FirebaseUser user =
                FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){

            emailText.setText(user.getEmail());

            String email =
                    user.getEmail();

            if(email != null){

                String name =
                        email.split("@")[0];

                nameText.setText(name);
            }
        }

        changePasswordBtn.setOnClickListener(v -> {

            if(user != null){

                FirebaseAuth.getInstance()
                        .sendPasswordResetEmail(user.getEmail());

                Toast.makeText(
                        this,
                        "Password reset email sent",
                        Toast.LENGTH_SHORT
                ).show();

            }

        });

        logoutBtn.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();

            startActivity(
                    new Intent(
                            ProfileActivity.this,
                            MainActivity.class
                    )
            );

            finish();

        });

    }
}