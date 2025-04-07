package com.example.vitalsync;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ActivenessActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ActivityLevelPrefs";
    private static final String ACTIVITY_LEVEL_KEY = "SelectedActivityLevel";

    private TextView sedentaryText, lowActiveText, activeText, veryActiveText;
    private ImageButton nextButton;
    private TextView backText;
    private String selectedActivityLevel = null;

    private Toast currentToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activeness);

        sedentaryText = findViewById(R.id.sedentary_text);
        lowActiveText = findViewById(R.id.low_active_text);
        activeText = findViewById(R.id.active_text);
        veryActiveText = findViewById(R.id.very_active_text);
        nextButton = findViewById(R.id.proceed_button);
        backText = findViewById(R.id.textView);

        loadSelectedActivityLevel();

        sedentaryText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectActivityLevel(sedentaryText);
                selectedActivityLevel = "Sedentary";
                saveSelectedActivityLevel("Sedentary");

                if (currentToast != null) currentToast.cancel();
                currentToast = Toast.makeText(ActivenessActivity.this, "Selected: Sedentary", Toast.LENGTH_SHORT);
                currentToast.show();
            }
        });

        lowActiveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectActivityLevel(lowActiveText);
                selectedActivityLevel = "Low Active";
                saveSelectedActivityLevel("Low Active");

                if (currentToast != null) currentToast.cancel();
                currentToast = Toast.makeText(ActivenessActivity.this, "Selected: Low Active", Toast.LENGTH_SHORT);
                currentToast.show();
            }
        });

        activeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectActivityLevel(activeText);
                selectedActivityLevel = "Active";
                saveSelectedActivityLevel("Active");

                if (currentToast != null) currentToast.cancel();
                currentToast = Toast.makeText(ActivenessActivity.this, "Selected: Active", Toast.LENGTH_SHORT);
                currentToast.show();
            }
        });

        veryActiveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectActivityLevel(veryActiveText);
                selectedActivityLevel = "Very Active";
                saveSelectedActivityLevel("Very Active");

                if (currentToast != null) currentToast.cancel();
                currentToast = Toast.makeText(ActivenessActivity.this, "Selected: Very Active", Toast.LENGTH_SHORT);
                currentToast.show();
            }
        });

        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedActivityLevel != null) {
                    Intent intent = new Intent(ActivenessActivity.this, HeightActivity.class);
                    intent.putExtra("selectedActivityLevel", selectedActivityLevel);
                    startActivity(intent);
                } else {
                    Toast.makeText(ActivenessActivity.this, "Please select an activity level", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectActivityLevel(TextView selectedButton) {
        sedentaryText.setBackgroundResource(R.drawable.bg_pink);
        lowActiveText.setBackgroundResource(R.drawable.bg_yellow);
        activeText.setBackgroundResource(R.drawable.bg_green);
        veryActiveText.setBackgroundResource(R.drawable.bg_pale_green);

        if (selectedButton == sedentaryText) {
            selectedButton.setBackgroundResource(R.drawable.bg_pink_selected);
        } else if (selectedButton == lowActiveText) {
            selectedButton.setBackgroundResource(R.drawable.bg_yellow_selected);
        } else if (selectedButton == activeText) {
            selectedButton.setBackgroundResource(R.drawable.bg_green_selected);
        } else if (selectedButton == veryActiveText) {
            selectedButton.setBackgroundResource(R.drawable.bg_pale_green_selected);
        }
    }

    private void saveSelectedActivityLevel(String activityLevel) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACTIVITY_LEVEL_KEY, activityLevel);
        editor.apply();
    }

    private void loadSelectedActivityLevel() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedActivityLevel = sharedPreferences.getString(ACTIVITY_LEVEL_KEY, null);

        if (savedActivityLevel != null) {
            selectedActivityLevel = savedActivityLevel;
            if (savedActivityLevel.equals("Sedentary")) {
                selectActivityLevel(sedentaryText);
            } else if (savedActivityLevel.equals("Low Active")) {
                selectActivityLevel(lowActiveText);
            } else if (savedActivityLevel.equals("Active")) {
                selectActivityLevel(activeText);
            } else if (savedActivityLevel.equals("Very Active")) {
                selectActivityLevel(veryActiveText);
            }
        }
    }
}