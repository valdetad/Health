package com.example.health;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class Weight extends AppCompatActivity {

    private static final String[] UNITS = {"kg", "lbs", "st"};

    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> adapterItems;
    private TextInputLayout weightInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        autoCompleteTextView = findViewById(R.id.weightBtn);
        weightInputLayout = findViewById(R.id.weightInputLayout);
        adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, UNITS);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                showHideWeightInput(editable.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
    }

    private void showHideWeightInput(String selectedUnit) {
        if(selectedUnit.equals(UNITS[0]) ||
                selectedUnit.equals(UNITS[1]) ||
                selectedUnit.equals(UNITS[2])) {
            weightInputLayout.setVisibility(View.VISIBLE);
        } else {
            weightInputLayout.setVisibility(View.GONE);
        }
    }
}
