package com.example.administrator.sample.practice.bar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.example.administrator.sample.R;

/**
 * Created by shizi on 2016/11/15 0015.
 */

public class BarActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private String TAG = "123";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        initView();
    }

    private void initView() {
        seekBar = (SeekBar) findViewById(R.id.seekbar_bar);
        seekBar.setSecondaryProgress(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(TAG, "onProgressChanged: ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStartTrackingTouch: ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStopTrackingTouch: ");
            }
        });
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//                seekBar.setProgress(i*10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        seekBar.setEnabled(false);
    }
}
