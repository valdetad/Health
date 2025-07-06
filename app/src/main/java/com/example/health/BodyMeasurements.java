package com.example.health;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class BodyMeasurements extends AppCompatActivity {

    private Button bodytemperatureBtn;
    private Button weightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_measurements);

        bodytemperatureBtn = findViewById(R.id.bodytemperatureBtn);
        weightBtn = findViewById(R.id.weightBtn);

        bodytemperatureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBodyTemperature();
            }
        });

        weightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeight();
            }
        });
    }

    public void openBodyTemperature() {
        Intent intent = new Intent(this, BodyTemperature.class);
        startActivity(intent);
    }

    public void openWeight() {
        Intent intent = new Intent(this, Weight.class);
        startActivity(intent);
    }
}
