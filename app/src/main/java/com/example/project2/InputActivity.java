package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    EditText widthInput, lengthInput, projectNameInput;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        widthInput = findViewById(R.id.widthInput);
        lengthInput = findViewById(R.id.lengthInput);
        projectNameInput = findViewById(R.id.projectNameInput);

        nextBtn = findViewById(R.id.nextBtn);

        nextBtn.setOnClickListener(v -> {

            String width =
                    widthInput.getText().toString().trim();

            String length =
                    lengthInput.getText().toString().trim();

            String projectName =
                    projectNameInput.getText().toString().trim();

            if (projectName.isEmpty()) {

                projectNameInput.setError(
                        "Unesite naziv projekta"
                );

                return;
            }

            if (width.isEmpty()) {

                widthInput.setError(
                        "Unesite širinu"
                );

                return;
            }

            if (length.isEmpty()) {

                lengthInput.setError(
                        "Unesite dužinu"
                );

                return;
            }

            Intent intent =
                    new Intent(
                            InputActivity.this,
                            FurnitureActivity.class
                    );

            intent.putExtra(
                    "projectName",
                    projectName
            );

            intent.putExtra(
                    "width",
                    width
            );

            intent.putExtra(
                    "length",
                    length
            );

            startActivity(intent);

        });
    }
}