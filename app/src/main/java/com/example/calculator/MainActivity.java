package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private static class ClickListener implements View.OnClickListener {

        int activityName;
        String[] metricsNames;
        float[] metricsModifiers;
        Context context;

        public ClickListener(Context context, int activityName, String[] metricsNames, float[] metricsModifiers) {
            this.activityName = activityName;
            this.metricsNames = metricsNames;
            this.metricsModifiers = metricsModifiers;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            CalculatorActivity.metricsModifiers = metricsModifiers;
            CalculatorActivity.metricsNames = metricsNames;
            CalculatorActivity.activityName = activityName;
            context.startActivity(new Intent(context, CalculatorActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        String[] namesLength = getResources().getStringArray(R.array.metrics_length);
        float[] modsLength = {1, 1/10f, 1/100f, 1/1000f, 1609.34f, 1/1.09361f, 1/3.28084f, 1/39.97f, 1000};
        findViewById(R.id.btn_length).setOnClickListener(new ClickListener(this, R.string.length, namesLength, modsLength));

        String[] namesSquare = getResources().getStringArray(R.array.metrics_square);
        float[] modsSquare = {1, 1/100f, 1/10000f, 1/1000000f, 2590000, 1/1.196f, 1/10.7639f, 1/1550f, 100, 10000};
        findViewById(R.id.btn_square).setOnClickListener(new ClickListener(this, R.string.square, namesSquare, modsSquare));

        String[] namesValue = getResources().getStringArray(R.array.metrics_value);
        float[] modsValue = {1, 1/1000000f, 1/1000000000f, 1/1.308f, 1/35.3147f, 1/61023.7f, 1/264.172f, 1/2113.38f, 1/1000f};
        findViewById(R.id.btn_value).setOnClickListener(new ClickListener(this, R.string.value, namesValue, modsValue));

        String[] namesWeight = getResources().getStringArray(R.array.metrics_weight);
        float[] modsWeight = {1, 1/1000f, 1/1000000f, 1/2.20462f, 1/35274f, 6.35029f, 1000, 100};
        findViewById(R.id.btn_weight).setOnClickListener(new ClickListener(this, R.string.weight, namesWeight, modsWeight));

        String[] namesSpeed = getResources().getStringArray(R.array.metrics_speed);
        float[] modsSpeed = {1, 1/60f, 1/3600f, 1/2.237f, 1000f, 1/0.06f, 1/3.6f};
        findViewById(R.id.btn_speed).setOnClickListener(new ClickListener(this, R.string.speed, namesSpeed, modsSpeed));

        String[] namesDensity = getResources().getStringArray(R.array.metrics_density);
        float[] modsDensity = {1, 1000f, 1, 1/1000f, 1000f};
        findViewById(R.id.btn_density).setOnClickListener(new ClickListener(this, R.string.density, namesDensity, modsDensity));

        String[] namesPressure = getResources().getStringArray(R.array.metrics_pressure);
        float[] modsPressure = {1, 1/1000f, 1/1000000f, 1/1000000f, 1/101325f, 1/133.3f};
        findViewById(R.id.btn_pressure).setOnClickListener(new ClickListener(this, R.string.pressure, namesPressure, modsPressure));

        String[] namesTime = getResources().getStringArray(R.array.metrics_time);
        float[] modsTime = {1, 60, 3600, 3600*24, 3600*24*7, 3600*24*7*365};
        findViewById(R.id.btn_time).setOnClickListener(new ClickListener(this, R.string.time, namesTime, modsTime));

        String[] namesInfo = getResources().getStringArray(R.array.metrics_info);
        float[] modsInfo = {1/8f, 1, 1024f, 1024*1024f, 1024*1024*1024f, 1024*1024*1024*1024f, 128};
        findViewById(R.id.btn_info).setOnClickListener(new ClickListener(this, R.string.info, namesInfo, modsInfo));

    }
}
