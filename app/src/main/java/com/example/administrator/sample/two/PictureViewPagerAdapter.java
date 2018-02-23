package com.example.administrator.sample.two;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.sample.R;
import com.example.administrator.sample.autoScrollViewPager.AutoViewPager;
import com.example.administrator.sample.autoScrollViewPager.BaseViewPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shizi on 2016/10/23 0023.
 */

public class PictureViewPagerAdapter extends BaseViewPagerAdapter<Picture>
        implements ViewPager.OnPageChangeListener{

    private Context mContext;

    public PictureViewPagerAdapter(Context context, List<Picture> data, AutoViewPager viewPager, OnAutoViewPagerItemClickListener listener) {
        super(context, data, viewPager, listener);
        mContext = context;
    }


    @Override
    public void loadImage(ImageView view, int position, Picture picture) {
        Picasso.with(mContext).load(picture.getPath()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(view);
    }
}
