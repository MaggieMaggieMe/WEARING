package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PrepareActivity extends AppCompatActivity {

    private int reclen = 5;//3秒自动跳转
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;
    private TextView pre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);

        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_prepare);
        MyApplication.getInstance().addActivity(this);

        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒

        //更改LEVEL数字
        Bundle bundle = getIntent().getExtras();
        final int number = bundle.getInt("number");
        pre = findViewById(R.id.pre_text_1);
        pre.setText("LEVEL " + number);

        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从准备界面跳转到倒计时界面

                Intent intent = new Intent(PrepareActivity.this, CountDown.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("number",number);
                intent.putExtras(bundle2);
                startActivity(intent);
                finish();
            }
        }, 3000);//延迟5S后发送handler信息

    }

         TimerTask task = new TimerTask() {
            @Override
            public void run() {
               runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    reclen--;
                }
            });
         }
     };
}
