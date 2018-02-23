package com.example.administrator.sample.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.sample.R;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageDetailFragment extends Fragment {
	private String mImageUrl;
	private ImageView mImageView;
	private ProgressBar progressBar;
	private PhotoViewAttacher mAttacher;
	private Toast mToast;
	private ImageButton file_download;
	private boolean Loadingsuccess = false;

	public static ImageDetailFragment newInstance(String imageUrl) {
		final ImageDetailFragment f = new ImageDetailFragment();

		final Bundle args = new Bundle();
		args.putString("url", imageUrl);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
		Log.i("lei", "imagedeailfrg urls: " + mImageUrl.length());
		initImageLoader(getActivity());
	}

	public static void initImageLoader(Context context) {
		//缓存文件的目录
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.memoryCacheExtraOptions(720, 1280) // max width, max height，即保存的每个缓存文件的最大长宽 
				.threadPoolSize(3) //线程池内线程的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
				.diskCacheSize(50 * 1024 * 1024)  // SD卡缓存的最大值
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// 由原先的discCache -> diskCache
				.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
				.writeDebugLogs() // Remove for release app
				.build();
		//全局初始化此配置  
		ImageLoader.getInstance().init(config);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
		mImageView = (ImageView) v.findViewById(R.id.image);
		file_download = (ImageButton) v.findViewById(R.id.file_download);
		mAttacher = new PhotoViewAttacher(mImageView);
		
		mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
			
			@Override
			public void onPhotoTap(View arg0, float arg1, float arg2) {
				getActivity().finish();
			}

//			@Override
//			public void onOutsidePhotoTap() {
//
//			}
		});

		file_download.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(Loadingsuccess){//是否已下载
					mImageView.setDrawingCacheEnabled(true);
					saveImageToGallery(getActivity(),mImageView.getDrawingCache());
					mImageView.setDrawingCacheEnabled(false);
					showToast("图片保存到【系统相册】！",getActivity());
				}else{
					showToast("保存图片失败",getActivity());
				}
			}
		});
		progressBar = (ProgressBar) v.findViewById(R.id.loading);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		/***
		 * ##
		 * 网址
		 */
		ImageLoader.getInstance().displayImage(mImageUrl, mImageView,options, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				progressBar.setVisibility(View.VISIBLE);
			}

			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				String message = null;
				switch (failReason.getType()) {
				case IO_ERROR:
					message = "下载错误";
					break;
				case DECODING_ERROR:
					message = "图片无法显示";
					break;
				case NETWORK_DENIED:
					message = "网络有问题，无法下载";
					break;
				case OUT_OF_MEMORY:
					message = "图片太大无法显示";
					break;
				case UNKNOWN:
					message = "未知的错误";
					break;
				}
				Loadingsuccess = false;
				showToast(message,getActivity());
				progressBar.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				progressBar.setVisibility(View.GONE);
				Loadingsuccess = true;
				mAttacher.update();
			}
		});
	}
	DisplayImageOptions	options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.mipmap.ic_default)
	.showImageForEmptyUri(R.mipmap.ic_default)
	.showImageOnFail(R.mipmap.ic_default)
	.cacheInMemory(true)
	.cacheOnDisk(true)
	.imageScaleType(ImageScaleType.EXACTLY)
	.bitmapConfig(Bitmap.Config.ARGB_8888)//Use .bitmapConfig(Bitmap.Config.RGB_565) in display options. Bitmaps in RGB_565 consume 2 times less memory than in ARGB_8888.
	.build();

	/**
	 * 弹出Toast信息，过滤重复弹出
	 * @param text
	 * @param context
	 */
    protected void showToast(String text,Context context) {
        if(mToast == null) {  
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);  
        } else {  
            mToast.setText(text);    
            mToast.setDuration(Toast.LENGTH_SHORT);  
        }  
        mToast.show();  
    }
    
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
    	}
        
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
    				file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
    }
}
