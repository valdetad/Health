package com.example.health;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HealthTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);
    }

    public void toggleAccordion(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        TextView content = view.findViewWithTag("content" + tag);

        toggleAccordionVisibility(content);
    }

    private void toggleAccordionVisibility(TextView content) {
        if (content.getVisibility() == View.VISIBLE) {
            content.setVisibility(View.GONE);
        } else {
            content.setVisibility(View.VISIBLE);
        }
    }
}
