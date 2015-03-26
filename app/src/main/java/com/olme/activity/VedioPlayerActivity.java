package com.olme.activity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.olme.R;

/**
 *
 *
 * SurfaceHolder
 * 		1. 简介 : 是 Surface 的控制器, 用于控制 SurfaceView 绘图, 处理画布上的动画, 渲染效果, 大小等;
 * 		2. 常用方法 :
 * 			-- abstract void addCallback(SurfaceHolder.Callback callback) : 添加一个 SurfaceHolder.Callback 接口对象, 监听 Surface 的开始结束绘制大小改变事件;
 * 			-- abstract Canvas lockCanvas() : 锁定画布, 可以获得 Canvas 对象, 之后就可以在 Canvas 上绘图了;
 *
 * SurfaceHolder.Callback接口 :
 * 		1. Surface 绘图边界 : 所有的绘图工作都在 Surface 创建之后才能进行, 在 Surface 销毁之前结束;
 * 		2. Callback 接口对应的 Surface 边界 : surfaceCreated() 方法在开始绘制时回调, surfaceDestroyed() 在 Surface 销毁前回调;
 * 		3. 该接口中的方法 :
 * 			-- surfaceChanged() : 在 Surface 大小改变时回调;
 * 			-- surfaceCreated() : 在 Surface 创建时回调;
 * 			-- surfaceDestroyed() : 在 Surface 销毁时回调;
 *
 * @author octopus
 *
 */

/**
 * Created by root on 15-3-25.
 */

public class VedioPlayerActivity extends Activity{

    private String url;							                /* 地址输入框, 带自动提示功能 */
    private SurfaceView surface_view;							/* 播放视频载体 */
    private Button play;										/* 播放按钮 */
    private Button pause;										/* 咱提供按钮 */
    private Button reset;										/* 重放按钮 */
    private Button stop;										/* 停止按钮 */
    private TextView nowTime;
    private TextView stopTime;
    private int length;
    private LinearLayout operationPanel;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;							/* 播放器 */
    private boolean ispause = false;
    private int position = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedioplayer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);   //全屏
        initViews();
        initData();
    }

    /**
     * 初始化成员变量中的组件变量
     */
    private void initViews() {
        System.out.println("------------->initViews");
        url = "http://192.168.137.1:8080/wxc/vedios/example.3gp";
        //url = "/sdcard/b.mp4";
        /* 通过 findViewById 获取相关方法 */
        operationPanel = (LinearLayout) findViewById(R.id.operation);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        surface_view = (SurfaceView) findViewById(R.id.surfaceView);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.back);
        stop = (Button) findViewById(R.id.gohead);
        nowTime = (TextView) findViewById(R.id.nowTime);
        stopTime = (TextView) findViewById(R.id.stopTime);
    }

    /**
     * 初始化相关数据变量
     */
    private void initData() {
        System.out.println("------------->initData");
        mediaPlayer = new MediaPlayer();
		/* 获取并设置 SurfaceHolder 对象 */
        surface_view.getHolder().setKeepScreenOn(true);                     /*保持屏幕高亮*/
        //surface_view.getHolder().setFixedSize(176, 144);							/* 设置视频大小比例 */
        surface_view.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);/* 设置视频类型 */
        surface_view.getHolder().addCallback(new surFaceView()); 								/* 为 SurfaceHolder 设置回调函数, 即 SurfaceHolder.Callback 子类对象 */
    }


    public void mediaplay(View view) {
        switch (view.getId()) {
            case R.id.play :
                play(0);
                break;
            case R.id.back :
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    ispause = true;
                } else {
                    if (ispause) {
                        mediaPlayer.start();
                        ispause = false;
                    }
                }
                break;
            case R.id.gohead :
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;
        }
    }

    private void play(int position) {
        try {
            Uri uri = Uri.parse(url);
            mediaPlayer.reset();
            // 设置多媒体流类型
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(this, uri);
            mediaPlayer.setDisplay(surface_view.getHolder());
            //mediaPlayer.prepare();
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new PreparedListener(position));
            surface_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operationPanel.setVisibility(View.VISIBLE);
                    //progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            operationPanel.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }, 5000);
                }
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private final class PreparedListener implements OnPreparedListener {
        private int position;

        private PreparedListener(int position) {
            this.position = position;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start();
            operationPanel.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            Integer time = mediaPlayer.getDuration();
            System.out.println("--------->time: " + time);
            String timeString = "";
            time = time / 1000;
            int temp = time / 60;
            timeString += time % 60;
            while(temp > 0) {
                String tempString = "" + temp % 60 + ":";
                timeString = tempString + timeString;
                temp = temp / 60;
            }
            stopTime.setText(timeString);
            if (position > 0)
                mediaPlayer.seekTo(position);
        }

    }


    private final class surFaceView implements SurfaceHolder.Callback {     //上面绑定的监听的事件

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {   //创建完成后调用
            System.out.println("--------->surfaceCreated");
            operationPanel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            if (position > 0) {
                play(position);
                position = 0;
            }
        }
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) { //activity调用过pase方法，保存当前播放位置
            if (mediaPlayer.isPlaying()) {
                position = mediaPlayer.getCurrentPosition();
                mediaPlayer.stop();
            }
        }
    }


    @Override
    protected void onDestroy() {
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }

}
