package com.example.administrator.sample.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aigestudio.wheelpicker.widgets.WheelDatePicker;
import com.example.administrator.sample.R;
import com.example.administrator.sample.practice.timeselector.TimeSelector;

/**
 * Created by shizi on 2016/11/17 0017.
 * #日历、日期选择相关使用
 *
 * #Note:使用日期选择的方式：
 *      方式一：本例中把源码拷贝过来
 *      方式二：在build.gradle中添加依赖：compile 'com.feezu.liuli:timeselector:1.1.3+'
 */

public class TimeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1;
    private TimeSelector timeSelector,timeSelector2;
    private WheelDatePicker dataPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicie_time);
        initView();
        initTiemPicker();
    }

    private void initTiemPicker() {
        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
            }
        }, "1989-01-30 00:00", "2018-12-31 00:00");

//        timeSelector.setIsLoop(false);
        timeSelector2  = new TimeSelector(this,new TimeSelector.ResultHandler(){

            @Override
            public void handle(String time) {
                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
            }
        },"1989-01-30 00:00", "2018-12-31 00:00");

        //#设置日期显示类型
        timeSelector2.setMode(TimeSelector.MODE.YMD);
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Toast.makeText(getApplicationContext(), "btn1", Toast.LENGTH_LONG).show();
                timeSelector.show();
                break;
            case R.id.btn2:
                Toast.makeText(getApplicationContext(), "btn2", Toast.LENGTH_LONG).show();
                timeSelector2.show();
                break;
            case R.id.btn3:
                Toast.makeText(getApplicationContext(), "btn3", Toast.LENGTH_LONG).show();

                dataPicker = new WheelDatePicker(this);


                break;
        }
    }

}
