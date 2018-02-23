package com.example.administrator.sample.satelitemenu;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.sample.R;

/**
 * Created by shizi on 2016/10/21 0021.
 */

public class SateliteActivity extends Activity implements View.OnClickListener{

    private SateliteMenu mSateliteMenu;
    private Button btn1,btn2,btn3,btn4;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_satelitemenu);

//        mSateliteMenu = (SateliteMenu) findViewById(R.id.sm_menu);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);


//        mSateliteMenu.setOnMenuItemClickListener(new SateliteMenu.onMenuItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), view.getTag().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_leftTop) {
//            mSateliteMenu.setPosition(SateliteMenu.Position.POS_LEFT_TOP);
//            return true;
//        }
//        if (id == R.id.action_rightTop) {
//            mSateliteMenu.setPosition(SateliteMenu.Position.POS_RIGHT_TOP);
//            return true;
//        }
//        if (id == R.id.action_leftBottom) {
//            mSateliteMenu.setPosition(SateliteMenu.Position.POS_LEFT_BOTTOM);
//            return true;
//        }
//        if (id == R.id.action_rightBottom) {
//            mSateliteMenu.setPosition(SateliteMenu.Position.POS_RIGHT_BOTTOM);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn1:
                mSateliteMenu.setPosition(SateliteMenu.Position.POS_LEFT_TOP);
                break;
            case R.id.btn2:
                mSateliteMenu.setPosition(SateliteMenu.Position.POS_RIGHT_TOP);
                break;
            case R.id.btn3:
                mSateliteMenu.setPosition(SateliteMenu.Position.POS_LEFT_BOTTOM);
                break;
        }

    }
}
