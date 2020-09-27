package com.example.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView editTextPersonName, editTextPersonLastName, editTextDate;
    private RadioButton isAProgrammerGroup;
    private CheckBox javaCheckbox, pythonCheckbox, jsCheckbox, goLangCheckbox, ccppCheckbox, csCheckbox;
    private Spinner genderSpinner;
    private Button btnSend, btnClear;

    private DatePickerDialog.OnDateSetListener birthdateTextViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.instantiate();

        this.addGenderSpinnerOptions();
        this.createDatePicker();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                openDataActivity();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void openDataActivity() {
        Intent intent = new Intent(this, DisplayDataActivity.class);
        intent.putExtra("nameView", " Hola! Mi nombre es: " + editTextPersonName.getText().toString() + " " + editTextPersonLastName.getText().toString());
        intent.putExtra("additionalDataView", "Soy " + genderSpinner.getSelectedItem().toString() + " y nací en fecha: " + editTextDate.getText().toString());
        intent.putExtra("languagesView", isAProgrammerGroup.isChecked() ? "Me gusta programar. Mis lenguajes favoritos son: " +
                this.getFavoriteLanguages() : "No me gusta la programación");

        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getFavoriteLanguages() {
        List<String> favoriteLanguages = new ArrayList<>();
        if ( javaCheckbox.isChecked() )
            favoriteLanguages.add("Java");
        if ( pythonCheckbox.isChecked() )
            favoriteLanguages.add("Python");
        if ( jsCheckbox.isChecked() )
            favoriteLanguages.add("JS");
        if ( goLangCheckbox.isChecked() )
            favoriteLanguages.add("Go Lang");
        if ( ccppCheckbox.isChecked() )
            favoriteLanguages.add("C/C++");
        if ( csCheckbox.isChecked() )
            favoriteLanguages.add("C#");

        return String.join(", ", favoriteLanguages);
    }

    private void instantiate() {
        editTextPersonName = (TextView) findViewById(R.id.editTextPersonName);
        editTextPersonLastName = (TextView) findViewById(R.id.editTextPersonLastName);
        genderSpinner = (Spinner) findViewById(R.id.spinnerGender);
        editTextDate = (TextView) findViewById(R.id.editTextDate);
        isAProgrammerGroup = (RadioButton) findViewById(R.id.radioButtonYes);
        javaCheckbox = (CheckBox) findViewById(R.id.javaCheckbox);
        pythonCheckbox = (CheckBox) findViewById(R.id.pythonCheckbox);
        jsCheckbox = (CheckBox) findViewById(R.id.jsCheckbox);
        goLangCheckbox = (CheckBox) findViewById(R.id.goLangCheckbox);
        ccppCheckbox = (CheckBox) findViewById(R.id.ccppCheckbox);
        csCheckbox = (CheckBox) findViewById(R.id.csCheckbox);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnClear = (Button) findViewById(R.id.btnClear);
    }

    private void addGenderSpinnerOptions() {

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.genders_array));

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(genderAdapter);
    }

    private void createDatePicker() {


        editTextDate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        birthdateTextViewListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        birthdateTextViewListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("MainActivity", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                editTextDate.setText(date);
            }
        };
    }
}