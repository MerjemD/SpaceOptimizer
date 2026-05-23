package com.example.project2;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    RelativeLayout roomLayout;

    TextView scoreText;
    TextView recommendationText;
    TextView roomSizeText;

    Button saveBtn;

    LinearLayout bedView;
    LinearLayout sofaView;
    LinearLayout tableView;
    LinearLayout closetView;
    LinearLayout tvView;
    LinearLayout deskView;
    LinearLayout chairView;
    LinearLayout bookshelfView;
    LinearLayout kitchenView;

    double roomWidth;
    double roomLength;
    String projectName;

    boolean bed;
    boolean sofa;
    boolean table;
    boolean closet;
    boolean tv;
    boolean desk;
    boolean chair;
    boolean bookshelf;
    boolean kitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        roomLayout = findViewById(R.id.roomLayout);

        scoreText = findViewById(R.id.scoreText);
        recommendationText = findViewById(R.id.recommendationText);
        projectName =
                getIntent().getStringExtra(
                        "projectName"
                );
        roomSizeText = findViewById(R.id.roomSizeText);

        saveBtn = findViewById(R.id.saveBtn);

        // FURNITURE XML ELEMENTS

        bedView = findViewById(R.id.bedView);
        sofaView = findViewById(R.id.sofaView);
        tableView = findViewById(R.id.tableView);
        closetView = findViewById(R.id.closetView);
        tvView = findViewById(R.id.tvView);
        deskView = findViewById(R.id.deskView);
        chairView = findViewById(R.id.chairView);
        bookshelfView = findViewById(R.id.bookshelfView);
        kitchenView = findViewById(R.id.kitchenView);

        // ROOM SIZE

        String widthText =
                getIntent().getStringExtra("width");

        String lengthText =
                getIntent().getStringExtra("length");

        if (widthText == null || widthText.isEmpty()) {
            widthText = "4";
        }

        if (lengthText == null || lengthText.isEmpty()) {
            lengthText = "4";
        }

        roomWidth = Double.parseDouble(widthText);
        roomLength = Double.parseDouble(lengthText);

        roomSizeText.setText(
                "Tlocrt: "
                        + roomWidth
                        + "m × "
                        + roomLength
                        + "m"
        );

        // RESPONSIVE SCALE

        int maxSize = 900;

        double scale;

        if (roomWidth > roomLength) {

            scale = maxSize / roomWidth;

        } else {

            scale = maxSize / roomLength;
        }

        int widthPx =
                (int) (roomWidth * scale);

        int heightPx =
                (int) (roomLength * scale);

        // MIN SIZE

        if (widthPx < 350) {
            widthPx = 350;
        }

        if (heightPx < 350) {
            heightPx = 350;
        }

        // APPLY ROOM SIZE

        ViewGroup.LayoutParams params =
                roomLayout.getLayoutParams();

        params.width = widthPx;
        params.height = heightPx;

        roomLayout.setLayoutParams(params);

        // GET FURNITURE

        bed = getIntent().getBooleanExtra("bed", false);
        sofa = getIntent().getBooleanExtra("sofa", false);
        table = getIntent().getBooleanExtra("table", false);
        closet = getIntent().getBooleanExtra("closet", false);
        tv = getIntent().getBooleanExtra("tv", false);
        desk = getIntent().getBooleanExtra("desk", false);
        chair = getIntent().getBooleanExtra("chair", false);
        bookshelf = getIntent().getBooleanExtra("bookshelf", false);
        kitchen = getIntent().getBooleanExtra("kitchen", false);

        // SHOW ELEMENTS

        if (bed) {
            bedView.setVisibility(View.VISIBLE);
        }

        if (sofa) {
            sofaView.setVisibility(View.VISIBLE);
        }

        if (table) {
            tableView.setVisibility(View.VISIBLE);
        }

        if (closet) {
            closetView.setVisibility(View.VISIBLE);
        }

        if (tv) {
            tvView.setVisibility(View.VISIBLE);
        }

        if (desk) {
            deskView.setVisibility(View.VISIBLE);
        }

        if (chair) {
            chairView.setVisibility(View.VISIBLE);
        }

        if (bookshelf) {
            bookshelfView.setVisibility(View.VISIBLE);
        }

        if (kitchen) {
            kitchenView.setVisibility(View.VISIBLE);
        }

        // ANALYSIS

        double roomArea =
                roomWidth * roomLength;

        double usedArea = 0;

        if (bed) usedArea += 4;
        if (sofa) usedArea += 3;
        if (table) usedArea += 2;
        if (closet) usedArea += 3;
        if (tv) usedArea += 1.5;
        if (desk) usedArea += 2;
        if (chair) usedArea += 0.8;
        if (bookshelf) usedArea += 1.2;
        if (kitchen) usedArea += 5;

        double percentage =
                (usedArea / roomArea) * 100;

        int score =
                (int) (100 - percentage);

        if (score < 0) {
            score = 0;
        }

        if (score > 100) {
            score = 100;
        }

        scoreText.setText(score + "%");

        // RECOMMENDATIONS

        if (score >= 80) {

            scoreText.setTextColor(
                    Color.parseColor("#4CAF50")
            );

            recommendationText.setText(
                    "✔ Odlična organizacija prostora\n\n"
                            + "✔ Prostor djeluje otvoreno\n\n"
                            + "✔ Dobra iskorištenost prostora"
            );

        }

        else if (score >= 50) {

            scoreText.setTextColor(
                    Color.parseColor("#FFC107")
            );

            recommendationText.setText(
                    "⚠ Prostor je djelimično popunjen\n\n"
                            + "💡 Pomjerite namještaj uz zid\n\n"
                            + "💡 Ostavite više prostora za prolaz"
            );

        }

        else {

            scoreText.setTextColor(
                    Color.parseColor("#F44336")
            );

            recommendationText.setText(
                    "❌ Prostor je prenatrpan\n\n"
                            + "🚨 Previše velikih elemenata\n\n"
                            + "💡 Koristite manje dimenzije namještaja"
            );
        }

        // SAVE PROJECT

        int finalScore = score;

        saveBtn.setOnClickListener(v -> {

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

            count++;

            SharedPreferences.Editor editor =
                    preferences.edit();


            editor.putString(
                    "projectName" + count,
                    projectName
                            + " (" + finalScore + "%)"
            );

            editor.putFloat(
                    "roomWidth" + count,
                    (float) roomWidth
            );

            editor.putFloat(
                    "roomLength" + count,
                    (float) roomLength
            );

            editor.putBoolean(
                    "bed" + count,
                    bed
            );

            editor.putBoolean(
                    "sofa" + count,
                    sofa
            );

            editor.putBoolean(
                    "table" + count,
                    table
            );

            editor.putBoolean(
                    "closet" + count,
                    closet
            );

            editor.putBoolean(
                    "tv" + count,
                    tv
            );

            editor.putBoolean(
                    "desk" + count,
                    desk
            );

            editor.putBoolean(
                    "chair" + count,
                    chair
            );

            editor.putBoolean(
                    "bookshelf" + count,
                    bookshelf
            );

            editor.putBoolean(
                    "kitchen" + count,
                    kitchen
            );

            editor.putInt(
                    "score" + count,
                    finalScore
            );

            editor.putInt(
                    "projectCount",
                    count
            );

            editor.apply();

            Toast.makeText(
                    ResultActivity.this,
                    "Projekat sačuvan!",
                    Toast.LENGTH_SHORT
            ).show();

        });

    }
}