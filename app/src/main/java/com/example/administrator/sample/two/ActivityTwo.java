package com.example.administrator.sample.two;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.sample.R;
import com.example.administrator.sample.autoScrollViewPager.AutoScrollViewPager;
import com.example.administrator.sample.autoScrollViewPager.AutoViewPager;
import com.example.administrator.sample.autoScrollViewPager.BaseViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shizi on 2016/10/23 0023. *
 * *******图片轮播（引入），添加activity进入时动画
 * *******https://github.com/angeldevil/AutoScrollViewPager
 * *******进入时动画：https://my.oschina.net/u/556624/blog/102552
 * <p>
 * ***Q:
 * ***如果没有添加网络权限，不会显示图片，只是在图片位置显示为白色背景
 * ***如果有R文件找不到，可以把已经导入的R文件删掉，重新导入本工程下的R文件
 */

public class ActivityTwo extends AppCompatActivity {


    private AutoScrollViewPager autoScrollViewPager;
    private AutoViewPager mViewPager;
    private PictureViewPagerAdapter mAdapter;

    private String[] paths = {"https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=c493b482b47eca800d053ee7a1229712/8cb1cb1349540923abd671df9658d109b2de49d7.jpg",
            "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=45fbfa5555da81cb51e684cd6267d0a4/2f738bd4b31c8701491ea047237f9e2f0608ffe3.jpg",
            "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=ae0e95c0fc1986185e47e8847aec2e69/0b46f21fbe096b63eb314ef108338744ebf8ac62.jpg",
            "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=1fad2b46952397ddc9799f046983b216/dc54564e9258d109c94bbb13d558ccbf6d814de2.jpg",
            "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=ff0999f6d4160924c325a51be406359b/86d6277f9e2f070861ccd4a0ed24b899a801f241.jpg"};

    int[] resIds = new int[]{R.mipmap.img_big_1, R.mipmap.img_big_2, R.mipmap.img_big_1};
    private LoopViewPager looviewpager,looviewpager2;


    private MZBannerView mMZBannerView;
    private MZBannerView mNormalViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activityy_two);

        autoScrollViewPager = (AutoScrollViewPager) findViewById(R.id.viewpager_two);
        mViewPager = autoScrollViewPager.getViewPager();

        mAdapter = new PictureViewPagerAdapter(this, initData(), mViewPager
                , listener);
//                , new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener() {
//            @Override
//            public void onItemClick(int position, Object o) {
//
//            }
//        });


        initLoopView1();
        initLoopView2();

        initMZView1();
    }

    private List<Picture> initData() {
        List<Picture> data = new ArrayList<>();
        Picture picture;
        for (int i = 0; i < paths.length; i++) {
            picture = new Picture(paths[i], "图片" + i);
            data.add(picture);
        }
        return data;
    }


    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener listener
            = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener<Picture>() {
        @Override
        public void onItemClick(int position, Picture picture) {
//            Toast.makeText(getApplicationContext()
//                    , picture.getName(), Toast.LENGTH_SHORT).show();
        }
    };

    //************          *****************           ***************         ***************

    private void initLoopView2() {
        looviewpager2 = (LoopViewPager) findViewById(R.id.loop_pager1);
        looviewpager2.setAdapter(new LoopAdapter());
        looviewpager2.setOffscreenPageLimit(4);
        looviewpager2.setPageTransformer(true, new ViewPager.PageTransformer() {
            float scale = 0.9f;       //默认
//            float scale = 0.999f;

            @Override
            public void transformPage(View page, float position) {
                if (position >= 0 && position <= 1) {
                    page.setScaleY(scale + (1 - scale) * (1 - position));
                } else if (position > -1 && position < 0) {
                    page.setScaleY(1 + (1 - scale) * position);
                } else {
                    page.setScaleY(scale);
                }
            }
        });
        looviewpager2.autoLoop(true);
//        looviewpager.setCurrentItem(0);
    }

    private void initLoopView1() {
        looviewpager = (LoopViewPager) findViewById(R.id.loop_pager);
        looviewpager.setAdapter(new LoopAdapter());
        looviewpager.setOffscreenPageLimit(4);
        looviewpager.setPageTransformer(true, new ViewPager.PageTransformer() {
            //            float scale = 0.9f;       //默认
            float scale = 0.999f;

            @Override
            public void transformPage(View page, float position) {
                if (position >= 0 && position <= 1) {
                    page.setScaleY(scale + (1 - scale) * (1 - position));
                } else if (position > -1 && position < 0) {
                    page.setScaleY(1 + (1 - scale) * position);
                } else {
                    page.setScaleY(scale);
                }
            }
        });
        looviewpager.autoLoop(true);
//        looviewpager.setCurrentItem(0);
    }



    // LoopViewPager adapter
    class LoopAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return resIds.length;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(ActivityTwo.this, R.layout.item_layout_loop_viewpager, null);
            ImageView itemImage = (ImageView) view.findViewById(R.id.item_loopviewpager_image);
            itemImage.setImageResource(resIds[position]);
            container.addView(view);
            final int pos = position;
            view.setOnClickListener(new View.OnClickListener() {    // click 事件
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityTwo.this,"" + pos,Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }



    //************          *****************           ***************         ***************

    @Override
    public void onPause() {
        super.onPause();
        mMZBannerView.pause();
        mNormalViewPager.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBannerView.start();
        mNormalViewPager.start();
    }

    private void initMZView1() {


        mMZBannerView = (MZBannerView) findViewById(R.id.mz_view_pager);
        mNormalViewPager = (MZBannerView) findViewById(R.id.normal_viewPager);



        mMZBannerView.setPages(mockData(), new MZHolderCreator<MZViewPagerHolder>() {
            @Override
            public MZViewPagerHolder createViewHolder() {
                return new MZViewPagerHolder();
            }
        });

        mNormalViewPager.setIndicatorRes(R.drawable.mz_indicator_normal,R.drawable.mz_indicator_selected);
        mNormalViewPager.setPages(mockData(), new MZHolderCreator<MZViewPagerHolder>() {
            @Override
            public MZViewPagerHolder createViewHolder() {
                return new MZViewPagerHolder();
            }
        });
    }
    private List<MZDataEntry> mockData(){
        List<MZDataEntry> list = new ArrayList<>();
        MZDataEntry dataEntry = null;
        for(int i=0;i<resIds.length;i++){
            dataEntry = new MZDataEntry();
            dataEntry.resId = resIds[i];
            dataEntry.desc = "The time you enjoy wasting , is not wasted.";
            list.add(dataEntry);
        }

        return list;
    }

    public static final class MZViewPagerHolder implements MZViewHolder<MZDataEntry> {
        private ImageView mImageView;
        private TextView mTitle;
        private TextView mDesc;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.mz_normal_banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.normal_banner_image);
            mDesc = (TextView) view.findViewById(R.id.page_desc);
            return view;
        }

        @Override
        public void onBind(Context context, int position, MZDataEntry data) {
            mImageView.setImageResource(data.resId);
            mDesc.setText(data.desc);
        }
    }


}














