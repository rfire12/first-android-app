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

        nameView.setText( intent.getExtras().getString("nameView"));
        additionalDataView.setText(intent.getExtras().getString("additionalDataView"));
        languagesView.setText(intent.getExtras().getString("languagesView"));
    }
}