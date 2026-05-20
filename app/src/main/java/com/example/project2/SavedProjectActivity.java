package com.example.project2;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SavedProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int projectId =
                getIntent().getIntExtra("projectId", 1);

        SharedPreferences preferences =
                getSharedPreferences("projects", MODE_PRIVATE);

        Intent intent =
                new Intent(
                        SavedProjectActivity.this,
                        ResultActivity.class
                );

        intent.putExtra(
                "width",
                String.valueOf(
                        preferences.getFloat(
                                "roomWidth" + projectId,
                                4
                        )
                )
        );

        intent.putExtra(
                "length",
                String.valueOf(
                        preferences.getFloat(
                                "roomLength" + projectId,
                                4
                        )
                )
        );

        intent.putExtra(
                "bed",
                preferences.getBoolean(
                        "bed" + projectId,
                        false
                )
        );

        intent.putExtra(
                "sofa",
                preferences.getBoolean(
                        "sofa" + projectId,
                        false
                )
        );

        intent.putExtra(
                "table",
                preferences.getBoolean(
                        "table" + projectId,
                        false
                )
        );

        intent.putExtra(
                "closet",
                preferences.getBoolean(
                        "closet" + projectId,
                        false
                )
        );

        startActivity(intent);

        finish();
    }
}