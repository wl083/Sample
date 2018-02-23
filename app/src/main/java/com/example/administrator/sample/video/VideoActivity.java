//package com.example.administrator.sample.video;
//
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.administrator.sample.R;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import io.vov.vitamio.MediaPlayer;
//import io.vov.vitamio.Vitamio;
//import io.vov.vitamio.widget.MediaController;
//import io.vov.vitamio.widget.VideoView;
//
///**
// * Created by lei on 2016/11/14 0014.
// * *视频播放
// */
//
//public class VideoActivity extends AppCompatActivity implements View.OnClickListener{
//
//    private VideoView videoView;
//    private Button btnPlayVitamio;
//
//    private String videoUrl = "http://baobab.wdjcdn.com/145076769089714.mp4";
////    private String videoUrl = "http://gslb.miaopai.com/stream/oxX3t3Vm5XPHKUeTS-zbXA__.mp4";
//
//    private long currentPosition;
//    private long position;
//    private String TAG = "123";
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        getSupportActionBar().hide();
//        setContentView(R.layout.activity_video);
//
//        Vitamio.isInitialized(this);
//        initView();
//
//
////        long pos = savedInstanceState.getLong("currentposition");
////        currentPosition = pos;
//        Log.i(TAG, "currentPosition is: "  + this.currentPosition);
//
//
////        videoView.setOnTimedTextListener(new MediaPlayer.OnTimedTextListener() {
////            @Override
////            public void onTimedText(String text) {
////
////            }
////
////            @Override
////            public void onTimedTextUpdate(byte[] pixels, int width, int height) {
////
////            }
////        });
////        TextView tv = new TextView(this);
////        tv.setText("小葵花课堂");
////        videoView.setMediaBufferingIndicator(tv);
//    }
//
//    private void initView() {
//        videoView = (VideoView) findViewById(R.id.video_vitamio);
//        btnPlayVitamio = (Button) findViewById(R.id.btn_vitamio_play);
//        btnPlayVitamio.setOnClickListener(this);
//
////
//////        videoView.setVideoPath(videoUrl);
////        videoView.setMediaController(new MediaController(this));
////        videoView.requestFocus();
////        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
////            @Override
////            public void onPrepared(MediaPlayer mp) {
//////                mp.setPlaybackSpeed(0.5f);
//////                mp.pause();
////            }
////        });
////        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
////            @Override
////            public boolean onInfo(MediaPlayer mp, int what, int extra) {
////                if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
////                    videoView.pause();
////                }
////                return true;
////            }
////        });
//    }
//
//    @Override
//    public void onClick(final View v) {
//        switch (v.getId()){
//            case R.id.btn_vitamio_play:
////                videoView.setVideoPath(videoUrl);
////                if (currentPosition==0L) {
//                    videoView.setVideoPath(videoUrl);
////                MediaController mediaController = new MediaController(this,videoView);
////                    videoView.setMediaController(mediaController);
////                mediaController.setVisibility(View.GONE);
//                    videoView.setMediaController(new MediaController(VideoActivity.this));
//                    videoView.requestFocus();
//                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                        @Override
//                        public void onPrepared(MediaPlayer mp) {
////                mp.setPlaybackSpeed(0.5f);
//                            position = mp.getDuration();
////                        videoView.seekTo(currentPosition);        //从指定位置开始播放
//                        }
//                    });
////                }else {
////                    videoView.seekTo(position);
//////                    videoView.start();
////                }
//                break;
//        }
//    }
//
////    @Override
////    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
////        super.onSaveInstanceState(outState, outPersistentState);
////        outState.putLong("currentposition",position);
////    }
//}
