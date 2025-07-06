package com.example.health;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BodyTemperature extends AppCompatActivity {

    private EditText editText;
    private ImageView smileyFaceImageView;
    private ImageView sickFaceImageView;
    private static final String SUCCESS_MSG = "Your temperature is normal!";
    private static final String ERROR_MSG = "Your temperature is outside the normal range; please visit with your doctor!";
    private static final double ACCEPTABLE_TEMPERATURE = 36;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_temperature);

        initializeViews();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                handleLoginAndUIUpdate(editable.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}
        });
    }

    private void initializeViews() {
        editText = findViewById(R.id.editText);
        smileyFaceImageView = findViewById(R.id.smileyFaceImageView);
        sickFaceImageView = findViewById(R.id.sickFaceImageView);
    }

    private void handleLoginAndUIUpdate(String temperature) {
        if (temperature.isEmpty()) {
            smileyFaceImageView.setVisibility(View.GONE);
            sickFaceImageView.setVisibility(View.GONE);
            return;
        }
        try {
            double tempValue = Double.parseDouble(temperature);
            int smileyResourceId = R.drawable.smiley_face;
            int sickResourceId = R.drawable.sick_face;

            if (tempValue == ACCEPTABLE_TEMPERATURE) {
                showToast(SUCCESS_MSG);
                displayFace(smileyFaceImageView, smileyResourceId, sickFaceImageView);
            } else if (tempValue > ACCEPTABLE_TEMPERATURE) {
                showToast(ERROR_MSG);
                displayFace(sickFaceImageView, sickResourceId, smileyFaceImageView);
            } else {
                showToast(SUCCESS_MSG);
                displayFace(smileyFaceImageView, smileyResourceId, sickFaceImageView);
            }
        } catch (NumberFormatException e) {
            Log.e("BodyTemperature", "Error parsing temperature input", e);
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void displayFace(ImageView showImageView, int showImageResource, ImageView hideImageView) {
        showImageView.setVisibility(View.VISIBLE);
        showImageView.setImageResource(showImageResource);
        hideImageView.setVisibility(View.GONE);
    }
}
