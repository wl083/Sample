package com.example.administrator.sample.utils.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.administrator.sample.activity.KeyBoardRadioButtonSwitchActivity;


/**
 * 点击软键盘区域以外自动关闭软键盘
 * @author xh2009cn
 */
public class CloseKeyboardOnOutsideContainer extends FrameLayout {

    /**
     * @param context context
     */
    public CloseKeyboardOnOutsideContainer(Context context) {
        this(context, null);
    }

    /**
     * @param context context
     * @param attrs attrs
     */
    public CloseKeyboardOnOutsideContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context context
     * @param attrs attrs
     * @param defStyle defStyle
     */
    public CloseKeyboardOnOutsideContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final KeyBoardRadioButtonSwitchActivity activity = (KeyBoardRadioButtonSwitchActivity) getContext();
        boolean isKeyboardShowing = KeyboardSwitchMethodUtils.isKeyboardShowing();
        boolean isEmotionPanelShowing = activity.isPanelShowing();
        if ((isKeyboardShowing || isEmotionPanelShowing) && event.getAction() == MotionEvent.ACTION_DOWN) {
            int touchY = (int) (event.getY());
            if (KeyboardSwitchMethodUtils.isTouchKeyboardOutside(activity, touchY)) {
                if (isKeyboardShowing) {
                    KeyboardSwitchMethodUtils.hideKeyboard(activity.getCurrentFocus());
                }
                if (isEmotionPanelShowing) {
                    activity.hidePanel();
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
