package com.example.administrator.sample.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.sample.R;
import com.example.administrator.sample.view.custom.LineChartViewActivity;

/**
 * Created by shizi on 2017/8/11.
 */

public class CustomViewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    /**
     * 折线图
     * @param view
     */
    public void lineChartView(View view) {
        startActivity(new Intent(CustomViewActivity.this,LineChartViewActivity.class));
    }
}
