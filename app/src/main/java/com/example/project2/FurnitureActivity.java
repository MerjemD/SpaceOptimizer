package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FurnitureActivity extends AppCompatActivity {

    CheckBox bedCheck, sofaCheck, tableCheck, closetCheck, tvCheck;
    CheckBox deskCheck, chairCheck, bookshelfCheck, kitchenCheck;

    EditText bedWidth, bedLength;
    EditText sofaWidth, sofaLength;
    EditText tableWidth, tableLength;
    EditText closetWidth, closetLength;
    EditText deskWidth, deskLength;
    EditText chairWidth, chairLength;
    EditText bookshelfWidth, bookshelfLength;
    EditText kitchenWidth, kitchenLength;

    Button analyzeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);

        // CHECKBOXES

        bedCheck = findViewById(R.id.bedCheck);
        sofaCheck = findViewById(R.id.sofaCheck);
        tableCheck = findViewById(R.id.tableCheck);
        closetCheck = findViewById(R.id.closetCheck);
        tvCheck = findViewById(R.id.tvCheck);

        deskCheck = findViewById(R.id.deskCheck);
        chairCheck = findViewById(R.id.chairCheck);
        bookshelfCheck = findViewById(R.id.bookshelfCheck);
        kitchenCheck = findViewById(R.id.kitchenCheck);

        // DIMENSIONS

        bedWidth = findViewById(R.id.bedWidth);
        bedLength = findViewById(R.id.bedLength);

        sofaWidth = findViewById(R.id.sofaWidth);
        sofaLength = findViewById(R.id.sofaLength);

        tableWidth = findViewById(R.id.tableWidth);
        tableLength = findViewById(R.id.tableLength);

        closetWidth = findViewById(R.id.closetWidth);
        closetLength = findViewById(R.id.closetLength);
        deskWidth = findViewById(R.id.deskWidth);
        deskLength = findViewById(R.id.deskLength);

        chairWidth = findViewById(R.id.chairWidth);
        chairLength = findViewById(R.id.chairLength);

        bookshelfWidth = findViewById(R.id.bookshelfWidth);
        bookshelfLength = findViewById(R.id.bookshelfLength);

        kitchenWidth = findViewById(R.id.kitchenWidth);
        kitchenLength = findViewById(R.id.kitchenLength);

        analyzeBtn = findViewById(R.id.analyzeBtn);

        // ROOM DATA

        String width =
                getIntent().getStringExtra("width");

        String length =
                getIntent().getStringExtra("length");

        String projectName =
                getIntent().getStringExtra("projectName");

        // BUTTON

        analyzeBtn.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            FurnitureActivity.this,
                            ResultActivity.class
                    );

            // ROOM

            intent.putExtra("width", width);
            intent.putExtra("length", length);

            // PROJECT NAME

            intent.putExtra(
                    "projectName",
                    projectName
            );

            // FURNITURE

            intent.putExtra("bed", bedCheck.isChecked());
            intent.putExtra("sofa", sofaCheck.isChecked());
            intent.putExtra("table", tableCheck.isChecked());
            intent.putExtra("closet", closetCheck.isChecked());
            intent.putExtra("tv", tvCheck.isChecked());

            intent.putExtra("desk", deskCheck.isChecked());
            intent.putExtra("chair", chairCheck.isChecked());
            intent.putExtra("bookshelf", bookshelfCheck.isChecked());
            intent.putExtra("kitchen", kitchenCheck.isChecked());
            intent.putExtra("deskWidth", deskWidth.getText().toString());
            intent.putExtra("deskLength", deskLength.getText().toString());

            intent.putExtra("chairWidth", chairWidth.getText().toString());
            intent.putExtra("chairLength", chairLength.getText().toString());

            intent.putExtra("bookshelfWidth", bookshelfWidth.getText().toString());
            intent.putExtra("bookshelfLength", bookshelfLength.getText().toString());

            intent.putExtra("kitchenWidth", kitchenWidth.getText().toString());
            intent.putExtra("kitchenLength", kitchenLength.getText().toString());

            // BED SIZE

            intent.putExtra(
                    "bedWidth",
                    bedWidth.getText().toString()
            );

            intent.putExtra(
                    "bedLength",
                    bedLength.getText().toString()
            );

            // SOFA SIZE

            intent.putExtra(
                    "sofaWidth",
                    sofaWidth.getText().toString()
            );

            intent.putExtra(
                    "sofaLength",
                    sofaLength.getText().toString()
            );

            // TABLE SIZE

            intent.putExtra(
                    "tableWidth",
                    tableWidth.getText().toString()
            );

            intent.putExtra(
                    "tableLength",
                    tableLength.getText().toString()
            );

            intent.putExtra(
                    "closetWidth",
                    closetWidth.getText().toString()
            );

            intent.putExtra(
                    "closetLength",
                    closetLength.getText().toString()
            );

            startActivity(intent);

        });
    }
}