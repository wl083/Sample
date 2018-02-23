package com.example.administrator.sample.autoScrollViewPager;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shizi on 2016/10/10.
 */

public class AutoViewPager extends ViewPager {

    private static final String TAG = "AutoViewPager";

    private int currentItem;

    private Timer mTimer;
    private AutoTask mTask;

    public AutoViewPager(Context context) {
        super(context);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void start(){
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer.schedule(new AutoTask(),3000,3000);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentItem = getCurrentItem();
            if(currentItem == getAdapter().getCount() - 1){
                currentItem = 0 ;
            }else {
                currentItem++ ;
            }
            setCurrentItem(currentItem);
        }
    };

    private AutoHandler mHandler = new AutoHandler();

    public void updatePointView(int size) {
        if (getParent() instanceof AutoScrollViewPager){
            AutoScrollViewPager pager = (AutoScrollViewPager) getParent();
            pager.initPointView(size);
        }else {
            Log.e(TAG,"parent view not be AutoScrollViewPager");
        }
    }

    public void onPageSelected(int position) {
        AutoScrollViewPager pager = (AutoScrollViewPager) getParent();
        pager.updatePointView(position);
    }

    private class AutoTask extends TimerTask{

        @Override
        public void run() {
            mHandler.post(runnable);
        }
    }

    private final static class AutoHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }

    public void onStop(){
        //先取消定时器
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void onDestroy(){
        onStop();
    }

    public void onResume(){
        start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG,"down");
                onStop();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG,"move");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG,"up");
                onResume();
                break;
        }
        return super.onTouchEvent(ev);
    }
}
