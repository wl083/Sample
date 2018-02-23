package com.example.administrator.sample.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.administrator.sample.R;
import com.example.administrator.sample.activity.KeyBoardRadioButtonSwitchActivity;
import com.example.administrator.sample.base.BaseActivity;
import com.example.administrator.sample.view.custom.LineChartViewActivity;

/**
 * Created by stone on 2017/8/11.
 */

public class CustomViewActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        findViewById(R.id.btn_custom_view_line_chart_view).setOnClickListener(this);
        findViewById(R.id.btn_custom_view_keyboard_switch).setOnClickListener(this);
    }

    /**
     * 折线图
     //     * @param view
     */
//    public void lineChartView(View view) {
//        startActivity(new Intent(CustomViewActivity.this,LineChartViewActivity.class));
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_custom_view_line_chart_view:                                              // 折线图
                startActivity(new Intent(CustomViewActivity.this,LineChartViewActivity.class));
                break;
            case R.id.btn_custom_view_keyboard_switch:                                              //软键盘和 radiobutton 切换问题
                startActivity(KeyBoardRadioButtonSwitchActivity.class);
                break;
        }
    }
}