package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView editTextPersonName, editTextPersonLastName, editTextDate;
    private RadioGroup isAProgrammerGroup;
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
    }

    private void instantiate() {
        editTextPersonName = (TextView) findViewById(R.id.editTextPersonName);
        editTextPersonLastName = (TextView) findViewById(R.id.editTextPersonLastName);
        editTextDate = (TextView) findViewById(R.id.editTextDate);
        isAProgrammerGroup = (RadioGroup) findViewById(R.id.isAProgrammerGroup);
        javaCheckbox = (CheckBox) findViewById(R.id.javaCheckbox);
        pythonCheckbox = (CheckBox) findViewById(R.id.pythonCheckbox);
        jsCheckbox = (CheckBox) findViewById(R.id.jsCheckbox);
        goLangCheckbox = (CheckBox) findViewById(R.id.goLangCheckbox);
        ccppCheckbox = (CheckBox) findViewById(R.id.ccppCheckbox);
        csCheckbox = (CheckBox) findViewById(R.id.csCheckbox);
        genderSpinner = (Spinner) findViewById(R.id.spinnerGender);
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