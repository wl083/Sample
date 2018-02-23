package com.example.administrator.sample.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.sample.base.BaseFragment;

/**
 * Created by stone
 * On 2018/2/8
 * Describe:
 */

public class MsgKeyboardFragment extends BaseFragment {

    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tvShow = new TextView(mContext);
        tvShow.setText("MsgKeyboardFragment");
        tvShow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tvShow.setGravity(Gravity.CENTER);
        return tvShow;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
