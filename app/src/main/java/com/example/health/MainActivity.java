package com.example.health;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button brushTeethBtn;
    private Button healthTipsBtn;
    private Button bodyMeasurementsBtn;
    private Button aboutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        brushTeethBtn = findViewById(R.id.brushTeethBtn);
        brushTeethBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrushingTime();
            }
        });

        healthTipsBtn = findViewById(R.id.healthTipsBtn);
        healthTipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHealthTips();
            }
        });

        bodyMeasurementsBtn = findViewById(R.id.bodyMeasurementsBtn);
        bodyMeasurementsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBodyMeasurements();
            }
        });

        aboutBtn = findViewById(R.id.aboutBtn);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item) {
            showLogoutConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                performLogout();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void performLogout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void openBrushingTime() {
        Intent intent = new Intent(this, BrushingTime.class);
        startActivity(intent);
    }

    public void openHealthTips() {
        Intent intent = new Intent(this, HealthTips.class);
        startActivity(intent);
    }

    public void openBodyMeasurements() {
        Intent intent = new Intent(this, BodyMeasurements.class);
        startActivity(intent);
    }

    public void openAboutActivity() {
        try {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e("Activity2", "Error starting AboutActivity", e);
        }
    }
}
