package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FailResultActivity extends AppCompatActivity {
    private Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_result);
        MyApplication.getInstance().addActivity(this);

        btn_home = (Button) findViewById(R.id.home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击“家”跳转到展示主页
                Intent intent = new Intent(FailResultActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
