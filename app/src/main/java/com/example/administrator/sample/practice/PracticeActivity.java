package com.example.administrator.sample.practice;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.sample.R;
import com.example.administrator.sample.practice.bar.BarActivity;
import com.example.administrator.sample.practice.calendar.CalendarActivity;
import com.example.administrator.sample.view.RoundProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import io.vov.vitamio.MediaPlayer;
//import io.vov.vitamio.Vitamio;


/**
 * Created by shizi on 2016/10/25 0025.
 *
 * *1、日期选择器（三级联动样式）
 * *2、语音识别
 * *3、图片相关应用
 * *4、圆形进度条
 *
 * Note：在使用语音识别时，如果手机（非模拟器）没有安装 voicesearch.apk ，则运行时会抛出 ActivityNotFound 异常，解决的方法就是在手机上安装一个该软件即可；
 *      需要添加下面权限：android.permission.INTERNET 和 android.permission.RECORD_AUDIO。
 */

public class PracticeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11;

    private TextView tvResult;
    private MediaPlayer mediaPlayer;

    private RoundProgressBar roundProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_test);

//        Vitamio.isInitialized(this);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.result_test);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(this);
        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(this);
        btn10 = (Button) findViewById(R.id.btn10);
        btn10.setOnClickListener(this);
        btn11 = (Button) findViewById(R.id.btn11);
        btn11.setOnClickListener(this);

        roundProgressBar = (RoundProgressBar) findViewById(R.id.progress_round);
        boolean state = 100 < 100;
        Log.i("lei", "state: " + state);

        findViewById(R.id.img_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PracticeActivity.this,"btn_img",Toast.LENGTH_SHORT).show();
                setRoundProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
//                showCurrentData();
                date2();
                break;
            case R.id.btn2:
                showDatePicker();
                break;
            case R.id.btn3:
                recoderVoice();
                break;
            //*使用PullToRefreshGridView
            case R.id.btn4:
                startActivity(new Intent(PracticeActivity.this,PullToRefreshActivity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(PracticeActivity.this,BarActivity.class));
                break;
            //* 使用 MediaPlay 播放语音
            case R.id.btn6:
                Toast.makeText(this,"btn6",Toast.LENGTH_SHORT).show();
                playRadio();

                String path = Environment.getExternalStorageDirectory().getPath();
                Log.i("123", "playRadio: " + path);
                break;
            //* 使用 MediaPlay 暂停播放语音
            case R.id.btn7:
                Toast.makeText(this,"btn7",Toast.LENGTH_SHORT).show();
                stopRadio();
                break;
            //* 使用 MediaPlay 暂停播放语音
            case R.id.btn8:
                Toast.makeText(this,"btn8",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PracticeActivity.this,CalendarActivity.class));
                break;  //* 使用 MediaPlay 暂停播放语音
            case R.id.btn9:
                Toast.makeText(this,"btn9",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PracticeActivity.this,TimeActivity.class));
                break;
            case R.id.btn10:
                Toast.makeText(this,"btn10",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PracticeActivity.this,PictureActivity.class));
                break;
            case R.id.btn11:
                Toast.makeText(this,"btn11",Toast.LENGTH_SHORT).show();
                setRoundProgress();
                break;
        }
    }

    int currentProgress = 0;
    private void setRoundProgress() {
        timeHandler.removeMessages(1);
        roundProgressBar.setVisibility(View.VISIBLE);

        Message msg = new Message();
        msg.what = 1;
        timeHandler.sendMessage(msg);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Message message = timeHandler.obtainMessage(1);
//        timeHandler.sendMessage(message);
    }

    /**
     * # 环形进度条计时器
     */
    Handler timeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                        if (currentProgress < 100){
                            currentProgress += 5;
                            Log.i("lei", "currentProgress: " + currentProgress);
                            roundProgressBar.setProgress(currentProgress);
                            Message msg1 = timeHandler.obtainMessage(1);
                            msg.what = 1;
                            try {
                                Thread.sleep(2000);
//                                timeHandler.sendMessageDelayed(msg1,100);
                                timeHandler.sendMessage(msg1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else if (currentProgress >= 100){
                            Log.i("lei", "currentProgress111: " + currentProgress);
                            roundProgressBar.setVisibility(View.INVISIBLE);
                        }
                    break;


            }

            super.handleMessage(msg);

        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        roundProgressBar.setVisibility(View.INVISIBLE);
    }

    private void stopRadio() {
        if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

    /**
     * 使用 MediaPlay 播放语音
     */
    private void playRadio() {
        String path1 = "file:///android_asset/xiaoxingyun.m4a";
        String path2 = "file:///android_asset/kang.mp3";
//        final MediaPlayer mediaPlayer = new MediaPlayer(PracticeActivity.this);
//         final MediaPlayer mediaPlayer = new MediaPlayer();
//         mediaPlayer = MediaPlayer.create(this,R.raw.hitta);
         mediaPlayer = MediaPlayer.create(this,R.raw.xiaoxingyun);
//        try {
//            mediaPlayer.setDataSource(path2);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    int duration = mediaPlayer.getDuration();
                }
            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                mediaPlayer.release();
//            }
//        });
        String path = Environment.getExternalStorageDirectory().getPath();
        Log.i("123", "playRadio: " + path);
    }

    private void recoderVoice() {
        try {
            //通过Intent传递语音识别的模式，开启语音
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            //语言模式和自由模式的语音识别
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            //提示语音开始
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "开始语音");

            /**
             * * 注意，如果手机上没有安装voicesearch.apk，在此处会抛出异常
             */
            //开始语音识别
            startActivityForResult(intent, 1234);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Log.i("123", "recoder: " + e.toString());
            Toast.makeText(getApplicationContext(), "找不到语音设备", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                if (resultCode == RESULT_OK && data != null && requestCode == 1234) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvResult.setText(result.get(0));
                }
                break;
            default:
                Toast.makeText(PracticeActivity.this, "接收出错", Toast.LENGTH_SHORT).show();
        }

    }


    private void showDatePicker() {
        int year = Integer.parseInt(date().substring(0,4));
        int month = Integer.parseInt(date().substring(5,7))-1;
        int day = Integer.parseInt(date().substring(8,10));
        DatePickerDialog datePickerDialog = new DatePickerDialog(PracticeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //*月份是从0开始计算的，所以要 +1，此时显示的才是真实的月份
                Toast.makeText(PracticeActivity.this,"您选择的日期为：" + year + "年" + (month+1) + "月" + dayOfMonth + "日",Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        datePickerDialog.show();

    }
    private void showCurrentData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String strData = format.format(date);
        Log.i("1234", "strData: " + strData);
        tvResult.setText(strData);

    }
    public String date(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String strData = format.format(date);
        getStringSub(strData);
        return strData;
    }

    private void getStringSub(String strData) {
        String year = strData.trim().substring(0,4);
        String month = strData.trim().substring(0,4);
        String day = strData.trim().substring(0,4);
        String hour = strData.trim().substring(0,4);
        String mins = strData.trim().substring(0,4);
    }

    /**
     * #直接获取年月日
     */
    public void date2(){
        Calendar c = Calendar.getInstance();
        int year_picker = c.get(Calendar.YEAR);
        int month_picker = c.get(Calendar.MONTH)+1;
        int date_picker = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR);
        int mins = c.get(Calendar.MINUTE);

        Log.i("1234", "strData: " + year_picker+"-"+month_picker+"-"+month_picker+"-"+date_picker+"-"+hour+""+mins);
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(year_picker).append("年")
                .append(month_picker).append("月")
                .append(date_picker).append("日")
                .append(" ")
                .append(hour).append(":")
                .append(mins);
        tvResult.setText(strBuf);
    }





}
