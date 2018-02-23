package com.example.administrator.sample.images;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.sample.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by shizi on 2016/11/28 0028.
 *
 * #点击图片（可能为多张图片）显示大图，并且能够进行缩放、保存到本地图片库等操作
 * # 需要添加依赖：uk-co-senab-photoview-source.jar
 *                  universal-image-loader-1.9.2.jar
 *
 *
 * #图片下拉变大demo：https://github.com/Frank-Zhu/PullZoomView
 * #图片缩放：https://github.com/chrisbanes/PhotoView
 */

public class PicturesActivity extends AppCompatActivity {

    private  DisplayImageOptions options;

    //# 图片URL
    private String[] urls = new String[]{"http://img0.imgtn.bdimg.com/it/u=1011366556,1214450084&fm=21&gp=0.jpg"
            ,"http://img3.imgtn.bdimg.com/it/u=2791147722,2554617715&fm=21&gp=0.jpg"
            ,"http://img3.imgtn.bdimg.com/it/u=4110119898,1522666303&fm=21&gp=0.jpg"};
//     private String[] urls = new String[]{"http://img0.imgtn.bdimg.com/it/u=1011366556,1214450084&fm=21&gp=0.jpg"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures);

        ImageView img = (ImageView) findViewById(R.id.img);
        initImageLoader();

        try {
            ImageLoader.getInstance().displayImage(urls[0],img,options);
        }catch (Exception e){
            Log.i("lei", "onCreate: " + e.toString());
        }



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("lei", "img click: " );
                Toast.makeText(PicturesActivity.this,"click",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PicturesActivity.this, ImagePagerActivity.class);

                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS,
                        urls);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX
                        ,0);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });


    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                PicturesActivity.this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheExtraOptions(480, 320, null).build();

        //#初始化 ImageLoader
        ImageLoader.getInstance().init(config);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_img_loading)
                .showImageForEmptyUri(R.drawable.ic_fail_empty)
                .showImageOnFail(R.drawable.ic_fail_empty)
//                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)// Use
                // .bitmapConfig(Bitmap.Config.RGB_565)
                // in display options.
                // Bitmaps in RGB_565
                // consume 2 times less
                // memory than in ARGB_8888.
                .build();
    }

}
