package com.example.administrator.sample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.sample.R;
import com.example.administrator.sample.base.BaseActivity;
import com.example.administrator.sample.fragments.MsgKeyboardFragment;
import com.example.administrator.sample.fragments.SetKeyboardFragment;
import com.example.administrator.sample.utils.keyboard.KeyboardSwitchMethodUtils;

/**
 * Created by stone
 * On 2018/2/8
 * Describe: edittext 存在时，软键盘和 Fragment/ViewPager(位于 activity 的底部) 之间的切换，可以查看本项目中的图片效果
 */

public class KeyBoardRadioButtonSwitchActivity extends BaseActivity implements View.OnTouchListener{

    private EditText edtEdit;

    private ImageView imgMessage,imgSetting;
    private RelativeLayout rlPanel;

    private Fragment msgFragment,setFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_radiobutton_switch);

        initView();
    }

    private void initView() {

        KeyboardSwitchMethodUtils.init(this);
        /**
         * 只有当在 Manifest 文件中 Activity 的 windowSoftInputMode 设置为 adjustResize 时才有效
         */
        KeyboardSwitchMethodUtils.detectKeyboardSwitchActivity(this);
//        KeyboardSwitchMethodUtils.enableCloseKeyboardOnTouchOutside(this);

        edtEdit = ((EditText) findViewById(R.id.edt_keyboard_switch));
        imgMessage = ((ImageView) findViewById(R.id.img_keyboard_message));
        imgSetting = (ImageView) findViewById(R.id.img_keyboard_setting);

        edtEdit.setOnTouchListener(this);
        imgMessage.setOnClickListener(switchListener);
        imgSetting.setOnClickListener(switchListener);

        rlPanel = (RelativeLayout) findViewById(R.id.rl_keyboard_switch);

        msgFragment = new MsgKeyboardFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rl_keyboard_switch,msgFragment).commit();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            KeyboardSwitchMethodUtils.setKeyboardShowing(true);
            imgMessage.setImageDrawable(getResources().getDrawable(R.drawable.tab_message_unselect));
            imgSetting.setImageDrawable(getResources().getDrawable(R.drawable.tab_setting_unselect));
            if (isPanelShowing()) {
                rlPanel.postDelayed(mHideEmotionPanelTask, 500);
            }
        }
        return false;
    }

    View.OnClickListener switchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPanel();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (v.getId()){
                case R.id.img_keyboard_message:
                    imgMessage.setImageDrawable(getResources().getDrawable(R.drawable.tab_message_select));
                    imgSetting.setImageDrawable(getResources().getDrawable(R.drawable.tab_setting_unselect));
                    hideFragment(setFragment,transaction);
                    if (msgFragment == null){
                        msgFragment = new MsgKeyboardFragment();
                        transaction.add(R.id.rl_keyboard_switch,msgFragment);
                    }else {
                        transaction.show(msgFragment);
                    }
                    break;
                case R.id.img_keyboard_setting:
                    imgMessage.setImageDrawable(getResources().getDrawable(R.drawable.tab_message_unselect));
                    imgSetting.setImageDrawable(getResources().getDrawable(R.drawable.tab_setting_select));
                    hideFragment(msgFragment,transaction);
                    if (setFragment == null){
                        setFragment = new SetKeyboardFragment();
                        transaction.add(R.id.rl_keyboard_switch,setFragment);
                    }else {
                        transaction.show(setFragment);
                    }
                    break;
            }
            transaction.commit();
        }
    };

    private void hideFragment(Fragment fragment, FragmentTransaction transaction){
        if (fragment != null) {
            transaction.hide(fragment);
        }
    }

    public boolean isPanelShowing() {
        return rlPanel.getVisibility() == View.VISIBLE;
    }

    public void showPanel() {
        rlPanel.removeCallbacks(mHideEmotionPanelTask);
        KeyboardSwitchMethodUtils.updateSoftInputMethod(this, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        rlPanel.setVisibility(View.VISIBLE);
        KeyboardSwitchMethodUtils.hideKeyboard(getCurrentFocus());
    }
    private Runnable mHideEmotionPanelTask = new Runnable() {
        @Override
        public void run() {
            hidePanel();
        }
    };

    public void hidePanel() {
        if (rlPanel.getVisibility() != View.GONE) {
            rlPanel.setVisibility(View.GONE);
            KeyboardSwitchMethodUtils.updateSoftInputMethod(this, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    public void updatePanelHeight(int keyboardHeight) {
        ViewGroup.LayoutParams params = rlPanel.getLayoutParams();
        if (params != null && params.height != keyboardHeight) {
            params.height = keyboardHeight;
            rlPanel.setLayoutParams(params);
        }
    }

}
