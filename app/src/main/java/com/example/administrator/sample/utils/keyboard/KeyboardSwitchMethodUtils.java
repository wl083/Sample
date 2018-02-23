package com.example.administrator.sample.utils.keyboard;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.administrator.sample.R;
import com.example.administrator.sample.activity.KeyBoardRadioButtonSwitchActivity;

import java.lang.reflect.Field;


/**
 * @author stone
 * 注意：只有当在 Manifest 文件中 Activity 的 windowSoftInputMode 设置为 adjustResize 时才有效
 * from:https://github.com/xh2009cn/KeyboardPanelSwitcher
 */
public class KeyboardSwitchMethodUtils {

    private static final float ROUND_CEIL = 0.5f;
    private static DisplayMetrics sDisplayMetrics;
    private static Resources sResources;
    private static int sDefaultKeyboardHeight;

    /**
     * 初始化操作
     *
     * @param context context
     */
    public static void init(Context context) {
        sDisplayMetrics = context.getResources().getDisplayMetrics();
        sResources = context.getResources();
        sDefaultKeyboardHeight = context.getResources().getDimensionPixelSize(R.dimen.default_keyboard_height);
    }

    /**
     * 监听软键盘弹出/关闭接口
     */
    public interface OnKeyboardEventListener {
        /**
         * 软键盘弹出
         */
        public void onSoftKeyboardOpened();
        /**
         * 软键盘关闭
         */
        public void onSoftKeyboardClosed();
    }

    private static boolean sIsKeyboardShowing;

    public static boolean isKeyboardShowing() {
        return sIsKeyboardShowing;
    }

    public static void setKeyboardShowing(boolean show) {
        sIsKeyboardShowing = show;
    }

    /**
     * 隐藏输入法
     *
     * @param currentFocusView 当前焦点view
     */
    public static void hideKeyboard(View currentFocusView) {
        if (currentFocusView != null) {
            IBinder token = currentFocusView.getWindowToken();
            if (token != null) {
                InputMethodManager im = (InputMethodManager) currentFocusView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(token, 0);
            }
        }
    }

    /**
     * 开关输入法
     *
     * @param currentFocusView 当前焦点view
     */
    public static void toggleSoftInput(View currentFocusView) {
        InputMethodManager imm = (InputMethodManager) currentFocusView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(currentFocusView, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 是否点击软键盘和输入法外面区域
     * @param activity 当前activity
     * @param touchY 点击y坐标
     */
    public static boolean isTouchKeyboardOutside(Activity activity, int touchY) {
        View editText = activity.getCurrentFocus();
        if (editText == null) {
            return false;
        }
        int[] location = new int[2];
        editText.getLocationOnScreen(location);
        int editY = location[1] - getStatusBarHeight();
        int offset = touchY - editY;
        if (offset > 0 && offset < editText.getMeasuredHeight()) {
            return false;
        }
        return true;
    }

    public static void enableCloseKeyboardOnTouchOutside(Activity activity) {
        CloseKeyboardOnOutsideContainer frameLayout = new CloseKeyboardOnOutsideContainer(activity);
        activity.addContentView(frameLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

//    public static void detectKeyboard(RecommendWriteTextEssayActivity activity) {
//        detectKeyboard(activity, null);
//    }
    public static void detectKeyboardSwitchActivity(KeyBoardRadioButtonSwitchActivity activity) {
        detectKeyboardSwitchActivity(activity, null);
    }

    /**
     * 只有当在 Manifest 文件中 Activity 的 windowSoftInputMode 设置为 adjustResize 时才有效
     */
    public static void detectKeyboardSwitchActivity(final KeyBoardRadioButtonSwitchActivity activity, final OnKeyboardEventListener listener) {
        final View activityRootView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        if (activityRootView != null) {
            ViewTreeObserver viewTreeObserver = activityRootView.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        final Rect r = new Rect();
                        activityRootView.getWindowVisibleDisplayFrame(r);
                        int heightDiff = getScreenHeight() - (r.bottom - r.top);
                        boolean show = heightDiff >= getDefaultKeyboardHeight() / 2;
                        if (show ^ sIsKeyboardShowing) {
                            sIsKeyboardShowing = show;
                            if (show) {
                                int keyboardHeight = heightDiff - getStatusBarHeight();
                                if (keyboardHeight != getDefaultKeyboardHeight()) {
//                                    getDefaultKeyboardHeight() = keyboardHeight;
                                    activity.updatePanelHeight(keyboardHeight);
                                }
                                if (listener != null) {
                                    listener.onSoftKeyboardOpened();
                                }
                            } else {
                                if (listener != null) {
                                    listener.onSoftKeyboardClosed();
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public static void updateSoftInputMethod(Activity activity, int softInputMode) {
        if (!activity.isFinishing()) {
            WindowManager.LayoutParams params = activity.getWindow().getAttributes();
            if (params.softInputMode != softInputMode) {
                params.softInputMode = softInputMode;
                activity.getWindow().setAttributes(params);
            }
        }
    }

    /**
     * 获取状态栏高度
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        final int defaultHeightInDp = 19;
        int height = dp2px(defaultHeightInDp);
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            height = sResources.getDimensionPixelSize(Integer.parseInt(field.get(obj).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }

    /**
     * dp 转 px
     *
     * @param dp dp值
     * @return 转换后的像素值
     */
    public static int dp2px(int dp) {
        return (int) (dp * sDisplayMetrics.density + ROUND_CEIL);
    }

    /**
     * 获取屏幕高度 单位：像素
     *
     * @return 屏幕高度
     */
    public static int getScreenHeight() {
        return sDisplayMetrics.heightPixels;
    }

    /**
     * 获取默认软键盘高度 单位：像素
     *
     * @return 默认软键盘高度
     */
    public static int getDefaultKeyboardHeight() {
        return sDefaultKeyboardHeight;
    }
}
