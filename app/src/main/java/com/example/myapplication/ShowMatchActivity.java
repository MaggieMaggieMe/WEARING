package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowMatchActivity extends AppCompatActivity {
    String hair,clothes,buttom,shoe,acc;
    String hair1,clothes1,buttom1,shoe1,acc1;
    String t;

    int number;
    private ImageView im;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private TextView degree;
    private Button btn_go;
    private ImageView target;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        MyApplication.getInstance().addActivity(this);
        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        im = findViewById(R.id.level1_2);

        //得到用户的搭配物品
        Bundle bundle = getIntent().getExtras();
        hair = bundle.getString("Hair");
        clothes = bundle.getString("clothes");
        buttom = bundle.getString("buttom");
        shoe = bundle.getString("shoe");
        acc = bundle.getString("acc");
        final Match yourMatch = new Match(hair,clothes,buttom,shoe,acc);

        //得到该关卡相应的搭配
        number = bundle.getInt("number");
        target = (ImageView) findViewById(R.id.level1);
        degree = (TextView)findViewById(R.id.degree);
        Resources res = getResources();
        final String packageName = getPackageName();
        int imageResId ;
        if (number==1) {
            hair1 = "hair_1";
            clothes1 = "clothes_2";
            buttom1 = "buttom_1";
            shoe1 = "shoe_2";
            acc1 = "acc_1";
            imageResId = res.getIdentifier("level1", "drawable", packageName);
            target.setImageDrawable(getResources().getDrawable(imageResId));
        }
        else {
            hair1 = "hair_2";
            clothes1 = "clothes_1";
            buttom1 = "buttom_2";
            shoe1 = "shoe_1";
            acc1 = "acc_2";
            imageResId = res.getIdentifier("level2", "drawable", packageName);
            target.setImageDrawable(getResources().getDrawable(imageResId));
        }
        final Match targetMatch = new Match(hair1,clothes1,buttom1,shoe1,acc1);

        changeMatchImag(yourMatch);

        //计算还原度
       degree.setText(degree(yourMatch,targetMatch));


       //判定时候闯关成功并跳转
        btn_go = (Button) findViewById(R.id.go_1);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转到是否闯关成功界面
                //如果成功，改变用户储存信息并跳转到相应界面
                if(result(yourMatch,targetMatch)==true){

                    if(number==1){
                        mEditor.putBoolean("level1",true);
                    }
                    if(number==2){
                        mEditor.putBoolean("level2",true);
                    }
                    mEditor.apply();
                    Intent intent = new Intent(ShowMatchActivity.this, PassResultActivity.class);
                    startActivity(intent);
                }
                //如果失败，直接跳转至相应界面
                else {
                    Intent intent = new Intent(ShowMatchActivity.this, FailResultActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    //计算还原度
    public String degree(Match yourMatch,Match targetMatch){
        int correct=0;
        String result = "";

        if(yourMatch.getHairId()==targetMatch.getHairId())
            correct++;
        if(yourMatch.getClothesId()==targetMatch.getClothesId())
            correct++;
        if(yourMatch.getButtomId()==targetMatch.getButtomId())
            correct++;
        if(yourMatch.getShoesId()==targetMatch.getShoesId())
            correct++;
        if(yourMatch.getAccessorieId()==targetMatch.getAccessorieId())
            correct++;

        result += correct/5*0.01+"%";

        return result;
    }

    //判断是否闯关成功
    public boolean result(Match yourMctch,Match targetMatch){
        boolean isPassed = false;
        if(yourMctch.toString()==targetMatch.toString())
            isPassed = true;
        return isPassed;
    }

    //根据选择的物品选择对应的图片来改变“你的搭配”中的图片
    public void changeMatchImag(Match yourMatch){
        String yourMatchText = yourMatch.toString();

        Resources res = getResources();
        final String packageName = getPackageName();
        int imageResId;

        switch (yourMatchText){
            case "hair_1 clothes_1 buttom_1 shoe_1 acc_1":
                imageResId = res.getIdentifier("match1", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_1 buttom_1 shoe_1 acc_2":
                imageResId = res.getIdentifier("match2", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_1 buttom_1 shoe_2 acc_1":
                imageResId = res.getIdentifier("match3", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_1 buttom_1 shoe_2 acc_2":
                imageResId = res.getIdentifier("match4", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_1 buttom_2 shoe_1 acc_1":
                imageResId = res.getIdentifier("match5", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_1 buttom_2 shoe_1 acc_2":
                imageResId = res.getIdentifier("match6", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_1 buttom_2 shoe_2 acc_1":
                imageResId = res.getIdentifier("match7", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_1 buttom_2 shoe_2 acc_2":
                imageResId = res.getIdentifier("match8", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_2 buttom_1 shoe_1 acc_1":
                imageResId = res.getIdentifier("match9", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_2 buttom_1 shoe_1 acc_2":
                imageResId = res.getIdentifier("match10", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_2 buttom_1 shoe_2 acc_1":
                imageResId = res.getIdentifier("level1", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_2 buttom_1 shoe_2 acc_2":
                imageResId = res.getIdentifier("match12", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_2 buttom_2 shoe_1 acc_1":
                imageResId = res.getIdentifier("match13", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_2 buttom_2 shoe_1 acc_2":
                imageResId = res.getIdentifier("match14", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_2 buttom_2 shoe_2 acc_1":
                imageResId = res.getIdentifier("match15", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_1 clothes_2 buttom_2 shoe_2 acc_2":
                imageResId = res.getIdentifier("match16", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_1 buttom_1 shoe_1 acc_1":
                imageResId = res.getIdentifier("match17", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_1 buttom_1 shoe_1 acc_2":
                imageResId = res.getIdentifier("match18", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_1 buttom_1 shoe_2 acc_1":
                imageResId = res.getIdentifier("match19", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_1 buttom_1 shoe_2 acc_2":
                imageResId = res.getIdentifier("match20", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_1 buttom_2 shoe_1 acc_1":
                imageResId = res.getIdentifier("match21", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_1 buttom_2 shoe_1 acc_2":
                imageResId = res.getIdentifier("level2", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_1 buttom_2 shoe_2 acc_1":
                imageResId = res.getIdentifier("match23", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_1 buttom_2 shoe_2 acc_2":
                imageResId = res.getIdentifier("match24", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_2 buttom_1 shoe_1 acc_1":
                imageResId = res.getIdentifier("match25", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_2 buttom_1 shoe_1 acc_2":
                imageResId = res.getIdentifier("match26", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_2 buttom_1 shoe_2 acc_1":
                imageResId = res.getIdentifier("match27", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_2 buttom_1 shoe_2 acc_2":
                imageResId = res.getIdentifier("match28", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_2 buttom_2 shoe_1 acc_1":
                imageResId = res.getIdentifier("match29", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_2 buttom_2 shoe_1 acc_2":
                imageResId = res.getIdentifier("match30", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_2 buttom_2 shoe_2 acc_1":
                imageResId = res.getIdentifier("match31", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;
            case "hair_2 clothes_2 buttom_2 shoe_2 acc_2":
                imageResId = res.getIdentifier("match32", "drawable", packageName);
                im.setImageDrawable(getResources().getDrawable(imageResId));
                break;

        }

    }

}
