package com.example.calculator;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    CalculatorActivity context;

    public static String[] metricsNames;
    public static float[] metricsModifiers;
    public static int activityName;
    public int metricsIDA = 0, metricsIDB = 0;

    protected static class TextWatcher implements android.text.TextWatcher {
        int answerFieldID;
        CalculatorActivity context;

        public TextWatcher(CalculatorActivity context, int answerFieldID) {
            this.context = context;
            this.answerFieldID = answerFieldID;
        }

        public static void calculate(CalculatorActivity context, int answerFieldID) {
            EditText answerEditText = context.findViewById(answerFieldID);
            if (!context.findViewById(answerFieldID).hasFocus()) {
                String answer = "";
                try {
                    float modifier;
                    String input;
                    if (answerFieldID == R.id.field_b) {
                        modifier = metricsModifiers[context.metricsIDA] / metricsModifiers[context.metricsIDB];
                        input = ((EditText) context.findViewById(R.id.field_a)).getText().toString();
                    } else {
                        modifier = metricsModifiers[context.metricsIDB] / metricsModifiers[context.metricsIDA];
                        input = ((EditText) context.findViewById(R.id.field_b)).getText().toString();
                    }
                    answer = Float.toString(Float.parseFloat(input) * modifier);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                answerEditText.setText(answer);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculate(context, answerFieldID);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        context = this;

        findViewById(R.id.field_a).requestFocus();

        ((TextView) findViewById(R.id.name)).setText(activityName);

        ((EditText) findViewById(R.id.field_a)).addTextChangedListener(new TextWatcher(this, R.id.field_b));
        ((EditText) findViewById(R.id.field_b)).addTextChangedListener(new TextWatcher(this, R.id.field_a));

        NumberPicker numberPickerA = (NumberPicker) findViewById(R.id.metrics_picker_a);
        NumberPicker numberPickerB = (NumberPicker) findViewById(R.id.metrics_picker_b);

        numberPickerA.setMinValue(0);
        numberPickerA.setMaxValue(metricsNames.length - 1);
        numberPickerA.setDisplayedValues(metricsNames);
        numberPickerA.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                metricsIDA = newVal;
                if (findViewById(R.id.field_a).hasFocus()) TextWatcher.calculate(context, R.id.field_b);
                else TextWatcher.calculate(context, R.id.field_a);
            }
        });

        numberPickerB.setMinValue(0);
        numberPickerB.setMaxValue(metricsNames.length - 1);
        numberPickerB.setDisplayedValues(metricsNames);
        numberPickerB.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                metricsIDB = newVal;
                if (findViewById(R.id.field_a).hasFocus()) TextWatcher.calculate(context, R.id.field_b);
                else TextWatcher.calculate(context, R.id.field_a);
            }
        });
    }
}