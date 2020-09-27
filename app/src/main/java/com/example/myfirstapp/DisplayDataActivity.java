package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayDataActivity extends AppCompatActivity {

    private TextView nameView, additionalDataView, languagesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        Intent intent = getIntent();

        nameView = findViewById(R.id.nameView);
        additionalDataView = findViewById(R.id.additionalDataView);
        languagesView = findViewById(R.id.languagesView);

        nameView.setText("Hola! Mi nombre es: " + intent.getExtras().getString("fullName"));
        additionalDataView.setText("Soy " + intent.getExtras().getString("gender") + " y nací en fecha: " + intent.getExtras().getString("birthdate"));
    }
}