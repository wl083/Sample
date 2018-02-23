package com.example.administrator.sample.autoScrollViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shizi on 2016/10/11.
 */

public abstract class BaseViewPagerAdapter<T> extends PagerAdapter implements ViewPager.OnPageChangeListener{

    private List<T> data = new ArrayList<>();

    private Context mContext;
    private AutoViewPager mView;

    private OnAutoViewPagerItemClickListener listener;

    public BaseViewPagerAdapter(List<T> t) {
        this.data = t;
    }

    public BaseViewPagerAdapter(Context context, AutoViewPager viewPager) {
        this.mContext = context;
        mView = viewPager;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);
        mView.setCurrentItem(0);
    }


    public BaseViewPagerAdapter(Context context, AutoViewPager viewPager,OnAutoViewPagerItemClickListener listener) {
        this.mContext = context;
        mView = viewPager;
        this.listener = listener;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);
        mView.setCurrentItem(0);
    }

    public BaseViewPagerAdapter(Context context, List<T> data,AutoViewPager viewPager,OnAutoViewPagerItemClickListener listener) {
        this.mContext = context;
        mView = viewPager;
        this.data = data;
        this.listener = listener;
        mView.setAdapter(this);
        mView.addOnPageChangeListener(this);
        mView.setCurrentItem(0);

        mView.start();
        mView.updatePointView(getRealCount());
    }

    public void setListener(OnAutoViewPagerItemClickListener listener) {
        this.listener = listener;
    }

    public void add(T t){
        data.add(t);
        notifyDataSetChanged();
        mView.updatePointView(getRealCount());
    }

    @Override
    public int getCount() {
        return data == null ? 0 : Integer.MAX_VALUE;
    }

    public int getRealCount(){
        return data == null ? 0 : data.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView view = (ImageView) LayoutInflater.from(mContext)
//                .inflate(R.layout.imageview,container,false);
                    .inflate(R.layout.imageview,container,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(position % getRealCount(),data.get(position % getRealCount()));
                }
            }
        });

        loadImage(view,position, data.get(position % getRealCount()));
        container.addView(view);

        return view;
    }

    public abstract void loadImage(ImageView view,int position,T t);

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mView.onPageSelected(position % getRealCount());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnAutoViewPagerItemClickListener<T> {
        void onItemClick(int position, T t);
    }
}
