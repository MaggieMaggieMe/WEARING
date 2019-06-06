package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RecordActivity extends AppCompatActivity {

    private Button mBtnButton;
    private LinearLayout record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        MyApplication.getInstance().addActivity(this);
        mBtnButton = (Button) findViewById((R.id.btn_1));
        record = findViewById(R.id.record);

        SharedPreferences mes = getSharedPreferences("data", MODE_PRIVATE);
        boolean level1 = mes.getBoolean("level1", false);
        boolean level2 = mes.getBoolean("level2", false);

        Resources res = getResources();
        final String packageName = getPackageName();
        int imageResId;

        if(level1&&level2){
            imageResId = res.getIdentifier("dd", "drawable", packageName);
            record.setBackground(getResources().getDrawable(imageResId));
        }
        if(level1&&!level2){
            imageResId = res.getIdentifier("bb", "drawable", packageName);
            record.setBackground(getResources().getDrawable(imageResId));

        }
        if(!level1&&level2){
            imageResId = res.getIdentifier("cc", "drawable", packageName);
            record.setBackground(getResources().getDrawable(imageResId));

        }
        if(!level1&&!level2){
            imageResId = res.getIdentifier("aa", "drawable", packageName);
            record.setBackground(getResources().getDrawable(imageResId));
        }


        mBtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //点击“返回”跳转到button主界面
                Intent intent = new Intent(RecordActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
