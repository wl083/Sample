package com.example.administrator.sample.practicetwo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.sample.R;

/**
 * Created by shizi on 2016/12/10 0010.
 *  # 滚动时显示/隐藏导航栏
 *  参考：http://lwk136.blog.51cto.com/8185131/1559276/
 */

public class ScrollHideAndShowToolbarActivity extends AppCompatActivity {

    private ScrollView mScrollView;
    private TextView mTv,mToolbar;
    private StringBuilder strBuild;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scroll_hide_show_toolbar);
        initView();
    }

    private void initView() {
        mScrollView = (ScrollView) findViewById(R.id.scrollview);
        mTv = (TextView) findViewById(R.id.tv_1);
        mToolbar = (TextView)findViewById(R.id.toolbar);
        mHandler = new Handler();
        strBuild = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            String str = getString(R.string.str);
            strBuild.append(str);
        }
        mTv.setText(strBuild);
//        mToolbar.setVisibility(View.GONE);

        setScroll();
    }

    private void setScroll() {
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            // 判断是否第一个滑动值
            boolean isFirst = true;
            // Scrollview滑动的ScrollY值
            private int ScrollY_Move;
            // 第一个滑动的ScrollY值
            private int First_ScrollY_Move;
            // 手指抬起时ScrollY值
            private int ScrollY_Up;
            // ScrollY_Move和First_ScrollY_Move的差值
            private int Cha;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //ACTIN_DOWN没用，不用监听
                    case MotionEvent.ACTION_MOVE:
                        if (isFirst) {
                            // 记录下第一个滑动的ScrollY值
                            First_ScrollY_Move = v.getScrollY();
                            Log.i("lei", "ACTION_MOVE-isFirst: " + First_ScrollY_Move);
                            isFirst = false;
                        }


                        // 记录下Scrollview滑动的ScrollY值
                        ScrollY_Move = v.getScrollY();

                        Log.i("lei", "ScrollY_Move: " + ScrollY_Move);
                        // 计算出ScrollY_Move和First_ScrollY_Move的差值
                        Cha = ScrollY_Move - First_ScrollY_Move;

                        // 当ScrollY_Move>First_ScrollY_Move时证明滑动方向是向下；
                        // 加上判断差值的目的：当Actionbar显示的时候，手指按住往下滑过一段距离还未抬起时，Actionbar也能隐藏
                        if (First_ScrollY_Move < ScrollY_Move || Cha > 200) {
                            // 隐藏Actionbar
                            mToolbar.setVisibility(View.GONE);
                        }

                        // 当ScrollY_Move<First_ScrollY_Move时证明滑动方向是向上；
                        // 加上判断差值的目的：当Actionbar显示的时候，手指按住往上滑过一段距离还未抬起时，Actionbar也能显示
                        // 判断ScrollY_Move < 150目的：当Scrollview滑动接近顶端时必须显示Actionbar
                        else if (First_ScrollY_Move > ScrollY_Move || Cha < -200
                                || ScrollY_Move < 150) {
                            mToolbar.setVisibility(View.VISIBLE);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        ScrollY_Up = v.getScrollY();// 记录下手指抬起时ScrollY值
                        isFirst = true;// 将isFirst还原为初始化
                        if (ScrollY_Up == 0) {
                            mToolbar.setVisibility(View.VISIBLE);
                        }
                        // Log.e("ACTION_UP--->", "scrollY_up:" + scrollY_up + "   "
                        // + "eventY_up:" + eventY_up);


                        /**
                         * # 手指抬起 3s 后，strBuild 消失
                         */
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mToolbar.isShown()){
                                    mToolbar.setVisibility(View.GONE);
                                }
                            }
                        },3000);

                        break;

                    default:
                        break;
                }
                return false;
            }

        });
    }
}
