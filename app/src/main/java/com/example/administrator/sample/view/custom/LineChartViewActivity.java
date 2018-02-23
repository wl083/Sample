package com.example.administrator.sample.view.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.sample.R;

import java.util.ArrayList;

/**
 * Created by shizi on 2017/8/11.
 */

public class LineChartViewActivity extends AppCompatActivity {

    private ArrayList<String> datesString;
    private ArrayList<Float> datesFloat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_line_chart_view);

        initDate();

        initView();
    }

    private void initView() {
        LineChartHybridView s1 = ((LineChartHybridView) findViewById(R.id.score_hybrid_float));
        LineChartHybridView s2 = (LineChartHybridView) findViewById(R.id.score_hybrid_string);
        s1.setDateWidtNumber(datesFloat);
        s2.setDateWithString(datesString);

        LineCHartSingleString singleString = ((LineCHartSingleString) findViewById(R.id.score_single_string));
        singleString.setDates(datesString);
        LineChartSingleFloat singleFloat = (LineChartSingleFloat) findViewById(R.id.score_single_float);
        singleFloat.setDate(datesFloat);

    }

    private void initDate() {
        datesString = new ArrayList<>();
        datesString.add("优");
        datesString.add("良");
        datesString.add("中");
        datesString.add("优");
        datesString.add("差");

        datesFloat = new ArrayList<>();
        datesFloat.add(10f);
        datesFloat.add(75f);
        datesFloat.add(65f);
        datesFloat.add(10f);
    }
}
