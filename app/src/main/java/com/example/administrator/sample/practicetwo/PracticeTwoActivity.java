package com.example.administrator.sample.practicetwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.sample.R;


/**
 * Created by shizi on 2016/12/10 0010.
 *
 * 1、列表/文本滚动时，导航栏显示/y隐藏
 */

public class PracticeTwoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1,btn2,btn3,btn4,btn5,btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_two);

        initView();

    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(ScrollHideAndShowToolbarActivity.class);
//                startActivity(new Intent(PracticeTwoActivity.this,ScrollHideAndShowToolbarActivity.class));
                break;
        }

    }



    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
}
