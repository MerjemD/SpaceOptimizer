package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FurnitureActivity extends AppCompatActivity {

    CheckBox bedCheck, sofaCheck, tableCheck, closetCheck, tvCheck;

    EditText bedWidth, bedLength;
    EditText sofaWidth, sofaLength;
    EditText tableWidth, tableLength;
    EditText closetWidth, closetLength;

    Button analyzeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);

        bedCheck = findViewById(R.id.bedCheck);
        sofaCheck = findViewById(R.id.sofaCheck);
        tableCheck = findViewById(R.id.tableCheck);
        closetCheck = findViewById(R.id.closetCheck);
        tvCheck = findViewById(R.id.tvCheck);

        bedWidth = findViewById(R.id.bedWidth);
        bedLength = findViewById(R.id.bedLength);

        sofaWidth = findViewById(R.id.sofaWidth);
        sofaLength = findViewById(R.id.sofaLength);

        tableWidth = findViewById(R.id.tableWidth);
        tableLength = findViewById(R.id.tableLength);

        closetWidth = findViewById(R.id.closetWidth);
        closetLength = findViewById(R.id.closetLength);

        analyzeBtn = findViewById(R.id.analyzeBtn);

        String width = getIntent().getStringExtra("width");
        String length = getIntent().getStringExtra("length");

        analyzeBtn.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            FurnitureActivity.this,
                            ResultActivity.class
                    );

            intent.putExtra("width", width);
            intent.putExtra("length", length);

            intent.putExtra("bed", bedCheck.isChecked());
            intent.putExtra("sofa", sofaCheck.isChecked());
            intent.putExtra("table", tableCheck.isChecked());
            intent.putExtra("closet", closetCheck.isChecked());
            intent.putExtra("tv", tvCheck.isChecked());

            intent.putExtra("bedWidth", bedWidth.getText().toString());
            intent.putExtra("bedLength", bedLength.getText().toString());

            intent.putExtra("sofaWidth", sofaWidth.getText().toString());
            intent.putExtra("sofaLength", sofaLength.getText().toString());

            intent.putExtra("tableWidth", tableWidth.getText().toString());
            intent.putExtra("tableLength", tableLength.getText().toString());

            intent.putExtra("closetWidth", closetWidth.getText().toString());
            intent.putExtra("closetLength", closetLength.getText().toString());

            startActivity(intent);

        });

    }
}