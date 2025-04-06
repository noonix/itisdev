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

public class WeightgoalActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "WeightGoalPrefs";
    private static final String WEIGHT_GOAL_KEY = "SelectedWeightGoal";

    private TextView loseWeightText;
    private TextView keepWeightText;
    private TextView gainWeightText;
    private ImageButton proceedButton;
    private TextView backTextView;
    private TextView selectedOption = null;
    private Toast currentToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weightgoal);

        loseWeightText = findViewById(R.id.lose_weight);
        keepWeightText = findViewById(R.id.keep_weight);
        gainWeightText = findViewById(R.id.gain_weight);
        proceedButton = findViewById(R.id.proceed_button);
        backTextView = findViewById(R.id.textView);

        loadSelectedWeightGoal();

        loseWeightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectWeightOption(loseWeightText);
                saveSelectedWeightGoal("Lose Weight");

                if (currentToast != null) currentToast.cancel();
                currentToast = Toast.makeText(WeightgoalActivity.this, "Selected: Lose Weight", Toast.LENGTH_SHORT);
                currentToast.show();
            }
        });

        keepWeightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectWeightOption(keepWeightText);
                saveSelectedWeightGoal("Keep Weight");

                if (currentToast != null) currentToast.cancel();
                currentToast = Toast.makeText(WeightgoalActivity.this, "Selected: Keep Weight", Toast.LENGTH_SHORT);
                currentToast.show();
            }
        });

        gainWeightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectWeightOption(gainWeightText);
                saveSelectedWeightGoal("Gain Weight");

                if (currentToast != null) currentToast.cancel();
                currentToast = Toast.makeText(WeightgoalActivity.this, "Selected: Gain Weight", Toast.LENGTH_SHORT);
                currentToast.show();
            }
        });

        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOption == null) {
                    Toast.makeText(WeightgoalActivity.this, "Please select a weight goal before proceeding", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(WeightgoalActivity.this, GenderActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void selectWeightOption(TextView selectedOptionView) {
        loseWeightText.setBackgroundResource(R.drawable.bg_lose_weight);
        keepWeightText.setBackgroundResource(R.drawable.bg_keep_weight);
        gainWeightText.setBackgroundResource(R.drawable.bg_gain_weight);

        if (selectedOptionView == loseWeightText) {
            selectedOptionView.setBackgroundResource(R.drawable.bg_lose_weight_selected);
        } else if (selectedOptionView == keepWeightText) {
            selectedOptionView.setBackgroundResource(R.drawable.bg_keep_weight_selected);
        } else if (selectedOptionView == gainWeightText) {
            selectedOptionView.setBackgroundResource(R.drawable.bg_gain_weight_selected);
        }

        selectedOption = selectedOptionView;
    }

    private void saveSelectedWeightGoal(String weightGoal) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WEIGHT_GOAL_KEY, weightGoal);
        editor.apply();
    }

    private void loadSelectedWeightGoal() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedGoal = sharedPreferences.getString(WEIGHT_GOAL_KEY, null);

        if (savedGoal != null) {
            if (savedGoal.equals("Lose Weight")) {
                selectWeightOption(loseWeightText);
            } else if (savedGoal.equals("Keep Weight")) {
                selectWeightOption(keepWeightText);
            } else if (savedGoal.equals("Gain Weight")) {
                selectWeightOption(gainWeightText);
            }
        }
    }
}