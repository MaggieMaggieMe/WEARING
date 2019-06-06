package com.example.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class CountDown extends AppCompatActivity{
    private TextView count;
    private int reclen = 3;//倒计时提示3秒
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;

    //目标图片
    private ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        MyApplication.getInstance().addActivity(this);

        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_count_down);
        initView();
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒


        Bundle bundle = getIntent().getExtras();
        final int number = bundle.getInt("number");

        //根据关卡数改变目标图片
        im = (ImageView) findViewById(R.id.level);
        Resources res = getResources();
        final String packageName = getPackageName();
        int imageResId;
        switch (number){
            case 1:
                imageResId = res.getIdentifier("level1", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case 2:
                imageResId = res.getIdentifier("level2", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
        }

        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从倒计时界面跳转到首界面
                Intent intent = new Intent(CountDown.this, MainActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("number",number);
                intent.putExtras(bundle3);
                startActivity(intent);
                finish();
            }
        }, 3000);//延迟3S后发送handler信息
    }


    private void initView() {
        count = findViewById(R.id.count);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    count.setText(String.valueOf(reclen));
                    reclen--;
                }
            });
        }
    };
}
