package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEdit;

    private EditText passwordEdit;

    private MediaPlayer mp = new MediaPlayer();

    private Button login;

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private CheckBox rememberPass;

    private TextView register;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        MyApplication.getInstance().addActivity(this);

        accountEdit = (EditText) findViewById(R.id.et_1);

        passwordEdit = (EditText) findViewById(R.id.et_2);

        login = (Button) findViewById(R.id.btn_Login);

        rememberPass = (CheckBox) findViewById(R.id.remember_password);

        register = (TextView) findViewById(R.id.btn_register);
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//注册按钮的下划线

        register.setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(intent);

            }

        });

        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        boolean isRemember = pref.getBoolean("remember_password", false);

        if(isRemember){

            //将账号和密码都设置在文本框内

            String account = pref.getString("account", "");

            String password = pref.getString("password", "");

            accountEdit.setText(account);

            passwordEdit.setText(password);

            rememberPass.setChecked(true);

        }

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.jaychou);

        login.setOnClickListener(new OnClickListener() {



            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub
                mp.start();

                String account = accountEdit.getText().toString();

                String password = passwordEdit.getText().toString();


                SharedPreferences pre = getSharedPreferences("data", MODE_PRIVATE);



                if(account.equals(pre.getString("account", ""))&&password.equals(pre.getString("password", ""))){

                    editor = pref.edit();

                    if (rememberPass.isChecked()) {

                        editor.putBoolean("remember_password", true);

                    }

                    else{

                        editor.clear();

                    }

                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                    startActivity(intent);

                    finish();

                }

                else {

                    Toast.makeText(LoginActivity.this, "用户名或密码不对", Toast.LENGTH_LONG).show();

                }

            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.stop();
            mp.release();
        }
    }

}
