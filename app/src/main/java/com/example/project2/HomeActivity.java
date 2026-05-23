package com.example.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Button newProjectBtn;
    Button logoutBtn;

    Button profileBtn;
    Button settingsBtn;

    EditText searchInput;

    ListView projectListView;

    ArrayList<String> projectList;
    ArrayList<String> filteredList;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        newProjectBtn =
                findViewById(R.id.newProjectBtn);

        logoutBtn =
                findViewById(R.id.logoutBtn);

        profileBtn =
                findViewById(R.id.profileBtn);

        settingsBtn =
                findViewById(R.id.settingsBtn);

        searchInput =
                findViewById(R.id.searchInput);

        projectListView =
                findViewById(R.id.projectListView);

        projectList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // ISPRAVLJENO: Umjesto android.R.layout.simple_list_item_1 sada koristi tvoj project_item
        adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.project_item,
                        filteredList
                );

        projectListView.setAdapter(adapter);

        loadProjects();

        // SEARCH
        searchInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after
            ) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count
            ) {

                filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });


        projectListView.setOnItemClickListener(
                (parent, view, position, id) -> {

                    String selectedProject =
                            filteredList.get(position);

                    int realIndex =
                            projectList.indexOf(selectedProject) + 1;

                    Intent intent =
                            new Intent(
                                    HomeActivity.this,
                                    SavedProjectActivity.class
                            );

                    intent.putExtra(
                            "projectId",
                            realIndex
                    );

                    startActivity(intent);

                });

        // NEW PROJECT
        newProjectBtn.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            HomeActivity.this,
                            InputActivity.class
                    )
            );

        });

        // PROFILE
        profileBtn.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            HomeActivity.this,
                            ProfileActivity.class
                    )
            );

        });

        // SETTINGS
        settingsBtn.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            HomeActivity.this,
                            SettingsActivity.class
                    )
            );

        });

        // LOGOUT
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

    }

    private void loadProjects() {

        projectList.clear();
        filteredList.clear();

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

            String project =
                    preferences.getString(
                            "projectName" + i,
                            "Projekat"
                    );

            projectList.add(project);

        }

        filteredList.addAll(projectList);

        adapter.notifyDataSetChanged();

    }

    private void filter(String text) {

        filteredList.clear();

        for (String project : projectList) {

            if (project.toLowerCase()
                    .contains(text.toLowerCase())) {

                filteredList.add(project);

            }

        }

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadProjects();
    }
}