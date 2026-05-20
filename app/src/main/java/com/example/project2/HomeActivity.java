package com.example.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button newProjectBtn, logoutBtn;

    ListView projectListView;

    ArrayList<String> projectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        newProjectBtn =
                findViewById(R.id.newProjectBtn);

        logoutBtn =
                findViewById(R.id.logoutBtn);

        projectListView =
                findViewById(R.id.projectListView);

        loadProjects();

        newProjectBtn.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            HomeActivity.this,
                            InputActivity.class
                    )
            );

        });

        logoutBtn.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();

            startActivity(
                    new Intent(
                            HomeActivity.this,
                            MainActivity.class
                    )
            );

            finish();

        });

        projectListView.setOnItemClickListener(
                (parent, view, position, id) -> {

                    Intent intent =
                            new Intent(
                                    HomeActivity.this,
                                    SavedProjectActivity.class
                            );

                    intent.putExtra(
                            "projectId",
                            position + 1
                    );

                    startActivity(intent);

                }
        );
    }

    private void loadProjects() {

        projectList = new ArrayList<>();

        SharedPreferences preferences =
                getSharedPreferences(
                        "projects",
                        MODE_PRIVATE
                );

        int count =
                preferences.getInt(
                        "projectCount",
                        0
                );

        for (int i = 1; i <= count; i++) {

            String name =
                    preferences.getString(
                            "projectName" + i,
                            "Projekat"
                    );

            int score =
                    preferences.getInt(
                            "score" + i,
                            0
                    );

            projectList.add(
                    name + "   •   " + score + "%"
            );
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.project_item,
                        projectList
                );

        projectListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadProjects();
    }
}