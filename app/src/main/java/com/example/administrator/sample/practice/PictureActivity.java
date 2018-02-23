package com.example.administrator.sample.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.sample.R;

/**
 * Created by shizi on 2016/11/21 0021.
 */

public class PictureActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1,btn2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_picture);
        initView();
    }

    private void initView() {

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
//        btn2 = (Button) findViewById(R.id.btn2);
//        btn2.setOnClickListener(this);
//        btn3 = (Button) findViewById(R.id.btn3);
//        btn3.setOnClickListener(this);
//        btn4 = (Button) findViewById(R.id.btn4);
//        btn4.setOnClickListener(this);
//        btn5 = (Button) findViewById(R.id.btn5);
//        btn5.setOnClickListener(this);
//        btn6 = (Button) findViewById(R.id.btn6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:

                break;
//            case R.id.btn2:
//                showDatePicker();
//                break;
//            case R.id.btn3:
//                recoderVoice();
//                break;
        }
    }
}
