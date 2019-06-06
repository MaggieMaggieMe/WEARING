package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class HomeActivity extends AppCompatActivity {
    private Button mBtn1,mBtn2,mBtn3;
    //所有关卡向量
    Vector<Level> totalLevels = new Vector<Level>();
    //可进入的关卡向量
    Vector<Level> availLevels = new Vector<Level>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        MyApplication.getInstance().addActivity(this);
        mBtn1 = findViewById(R.id.btn_1);
        mBtn2 = findViewById(R.id.btn_2);
        mBtn3 = findViewById(R.id.btn_3);
        OnClick onClick = new OnClick();
        mBtn1.setOnClickListener(onClick);
        mBtn2.setOnClickListener(onClick);
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(HomeActivity.this);
                customDialog.show();

            }
        });


        SharedPreferences mes = getSharedPreferences("data", MODE_PRIVATE);
        boolean level1 = mes.getBoolean("level1", false);
        boolean level2 = mes.getBoolean("level2", false);

        //创建第一个关卡
        Level levelone = new Level(1,level1);
        totalLevels.add(levelone);//加入关卡向量中

        //创建第二个关卡
        Level leveltwo = new Level(2,level2);
        totalLevels.add(leveltwo);//加入关卡向量中

    }

    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.btn_1:
                    //点击“开始”跳转到准备开始页面

                    //根据所选关卡更改准备页面的LEVEL数、目标页面的目标搭配图片和游戏页面的LEVEL数
                    Bundle bundle = new Bundle();
                    switch (chooseLevel()){
                        case 1:
                            bundle.putInt("number",1);
                            break;
                        case 2:
                            bundle.putInt("number",2);
                            break;
                    }
                    intent = new Intent(HomeActivity.this, PrepareActivity.class);
                    intent.putExtras(bundle);
                    break;

                case R.id.btn_2:
                    //点击“记录”跳转到record界面 
                    intent = new Intent(HomeActivity.this,RecordActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }


    //自动选择关卡
    public int chooseLevel(){
        int levelNum;

        //清空可选择关卡向量
        availLevels.clear();

        //循环所有关卡向量，将未闯关关卡添加进可选择关卡向量中
        for(Iterator i = totalLevels.iterator();i.hasNext();){
            Level level = (Level) i.next();
            if(level.getIsMarked()==false){
                availLevels.add(level);
            }
        }

        //从可选择关卡中随机选择
        Random random = new Random();
        Level beChoosed = (Level)availLevels.get((int)random.nextInt(availLevels.size()));
        levelNum = beChoosed.getNumber();

        //返回被选中关卡的号码
        return levelNum;
    }

}


