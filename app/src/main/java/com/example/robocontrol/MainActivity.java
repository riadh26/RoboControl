package com.example.robocontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT_HARVEST_COUNT = 500;

    private TextView harvestedCountTextView;
    private EditText customNumberInput;
    private Button startButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting views
        harvestedCountTextView = findViewById(R.id.harvestedCountText);
        startButton = findViewById(R.id.startButton);
        customNumberInput = findViewById(R.id.customNumberInput);
        cancelButton = findViewById(R.id.cancelButton);

        harvestedCountTextView.setText("0");
        cancelButton.setVisibility(View.GONE);

        startButton.setOnClickListener(clickEvent -> {
            int count = DEFAULT_HARVEST_COUNT;
            String input = customNumberInput.getText().toString();
            if (!input.isEmpty()) {
                count = Integer.parseInt(input);
                if (count <= 0) {
                    Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            customNumberInput.setEnabled(false);
            clickEvent.setEnabled(false);
            cancelButton.setVisibility(View.VISIBLE);
            String message = "Started harvesting " + count  + (count > 1 ? " tomatoes" : " tomato");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            // harvesting process starts here
        });

        cancelButton.setOnClickListener(clickEvent -> {
            clickEvent.setVisibility(View.GONE);
            customNumberInput.setEnabled(true);
            startButton.setEnabled(true);
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
        });
    }

}