package com.example.administrator.sample.practice.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.sample.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shizi on 2016/11/16 0016.
 */

public class CalendarActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener,View.OnClickListener{

    private MyGridView gridView;
    private String currentData = "";        //*当前日期
    private int year_c,month_c,day_c,width;
    private GestureDetector gestureDetector;
    private Bundle bundle;

    private TextView tvData;
    private ImageView imgPreviewMonth,imgNextMonth;


    @SuppressLint("SimpleDateFormat")
    public CalendarActivity() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        currentData = format.format(date);
        year_c = Integer.parseInt(currentData.split("-")[0]);
        month_c = Integer.parseInt(currentData.split("-")[1]);
        day_c = Integer.parseInt(currentData.split("-")[2]);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_calendar);

        gestureDetector = new GestureDetector(this);
        bundle = new Bundle();
        initView();
    }

    private void initView() {
        gridView = (MyGridView) findViewById(R.id.gridview);
        tvData = (TextView) findViewById(R.id.tv_data_homework_all);
        tvData.setOnClickListener(this);
        imgPreviewMonth = (ImageView) findViewById(R.id.img_preview_month_homework_all);
        imgPreviewMonth.setOnClickListener(this);
        imgNextMonth = (ImageView) findViewById(R.id.img_next_month_homework_all);
        imgNextMonth.setOnClickListener(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;

        showTable();
    }

    private void showTable() {
        tvData.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_preview_month_homework_all:
                break;
            case R.id.img_next_month_homework_all:
                break;
            case R.id.tv_data_homework_all:
                break;
        }
    }
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}
