package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {



    private Button button;

    private Button mBtn;

    //private Button readButton;

    private EditText name;

    private EditText account;

    private EditText password;

    private SharedPreferences mSharedPreferences;

    private SharedPreferences.Editor mEditor;

    String TAG = "MainActivity";



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_edit);
        MyApplication.getInstance().addActivity(this);


        mBtn = findViewById(R.id.btn_3);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击“返回”跳转到登录界面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        button = (Button) findViewById(R.id.btn_Sure);

        name = (EditText) findViewById(R.id.et_1);

        account = (EditText) findViewById(R.id.et_2);

        password = (EditText) findViewById(R.id.et_3);

        button.setOnClickListener(new OnClickListener() {



            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();

                editor.putString("name", name.getText().toString());

                editor.putString("account", account.getText().toString());

                editor.putString("password", password.getText().toString());

                editor.apply();

                mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);

                mEditor = mSharedPreferences.edit();

                mEditor.putBoolean("level1",false);

                mEditor.putBoolean("level2",false);

                mEditor.apply();

                finish();

            }

        });

    }



}

