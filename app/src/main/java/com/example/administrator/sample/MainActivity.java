package com.example.administrator.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.sample.base.BaseActivity;
import com.example.administrator.sample.images.PicturesActivity;
import com.example.administrator.sample.practice.PracticeActivity;
import com.example.administrator.sample.practicetwo.PracticeTwoActivity;
import com.example.administrator.sample.satelitemenu.SateliteActivity;
import com.example.administrator.sample.satelitemenu.SateliteMenu;
import com.example.administrator.sample.two.ActivityTwo;
import com.example.administrator.sample.view.CustomViewActivity;

/**
 * ***本项目中包括多种demo
 * 1、卫星式菜单（框架）
 * 2、图片轮播（引入），添加activity进入时动画
 * 3、图片大图浏览及缩放
 * 4、列表/文本滚动时，导航栏显示/y隐藏
 */

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        /**
         * ************卫星菜单
         */
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,SateliteActivity.class));
//            }
//        });
        //**图片轮播，使用框架
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,ActivityTwo.class));
//            }
//        });
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        //*小练习
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,PracticeActivity.class));
//            }
//        });
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,VideoActivity.class));
//            }
//        });
        /**
         * #点击跳转到图片缩放
         */
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,PicturesActivity.class));
//            }
//        });

        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        btn7 = ((Button) findViewById(R.id.btn7));
        btn7.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                startActivity(SateliteActivity.class);
                break;
            //**图片轮播，使用框架
            case R.id.btn2:
                startActivity(ActivityTwo.class);
                break;
            //*小练习
            case R.id.btn3:
                startActivity(PracticeActivity.class);
                break;
            case R.id.btn4:
//                startActivity(VideoActivity.class);
                break;
            /**
             * #点击跳转到图片缩放
             */
            case R.id.btn5:
                startActivity(PicturesActivity.class);
                break;
            /**
             * # 练习2
             */
            case R.id.btn6:
                startActivity(PracticeTwoActivity.class);
                break;
            case R.id.btn7: // 自定义view
                startActivity(CustomViewActivity.class);
                break;
        }
    }

//    private void startActivity(Class<?> cls){
//        Intent intent = new Intent(this,cls);
//        startActivity(intent);
//    }
}
