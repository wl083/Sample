package com.example.administrator.sample.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by stone
 * On 2018/2/8
 * Describe:
 */

public class BaseActivity extends AppCompatActivity {



    protected void startActivity(Class cls){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        startActivity(intent);
    }

    protected void startActivityForResult(Class cls,int requestCode){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        startActivityForResult(intent,requestCode);
    }

}
