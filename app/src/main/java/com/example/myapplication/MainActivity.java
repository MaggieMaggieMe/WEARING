package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fragment.AccessorieFragment;
import fragment.ButtomFragment;
import fragment.ClothesFragment;
import fragment.HairFragment;
import fragment.ShoesFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private ViewPager myviewpager;
    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;
    //选项卡中的按钮
    private Button btn_first;
    private Button btn_second;
    private Button btn_third;
    private Button btn_four;
    private Button btn_fifth;
    //作为指示标签的按钮
    private ImageView cursor;
    //标志指示标签的横坐标
    float cursorX = 0;
    //所有按钮的宽度的集合5
    private int[] widthArgs;
    //所有按钮的集合
    private Button[] btnArgs;
    private HairFragment hairFragment;
    private ClothesFragment clothesFragment;
    private ButtomFragment buttomFragment;
    private ShoesFragment shoesFragment;
    private AccessorieFragment accessorieFragment;


    //发型控件
    private RadioGroup group_hair;
    private RadioButton hair_1,hair_2;

    //上装控件
    private RadioGroup group_clothes;
    private RadioButton clothes_1,clothes_2;
    //下装控件
    private RadioGroup group_buttom;
    private RadioButton buttom_1,buttom_2;
    //鞋子控件
    private RadioGroup group_shoe;
    private RadioButton shoe_1,shoe_2;
    //配件控件
    private RadioGroup group_acc;
    private RadioButton acc_1,acc_2;


    //生成形象控件
    private Button btn_go;

    //关卡标题
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        MyApplication.getInstance().addActivity(this);
        initView();

        Bundle bundle = getIntent().getExtras();
        final int number = bundle.getInt("number");
        title = (TextView) findViewById(R.id.title_5);
        title.setText("LEVEL " + number);

        btn_go = (Button) findViewById(R.id.go);

        //记录所选物品，传递信息
        final Bundle bundleItem = new Bundle();
        //记录发型选项
        View view1 = View.inflate( this, (R.layout.layout_hair), null);
        hair_1 = view1.findViewById(R.id.choose_hair_1);
        hair_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("hair","hair_1");
            }
        });
        hair_2 = view1.findViewById(R.id.choose_hair_2);
        hair_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("hair","hair_2");
            }
        });

        //记录上装选项
        View view2 = View.inflate( this, (R.layout.layout_clothes), null);
        clothes_1 = view2.findViewById(R.id.clothes_1);
        hair_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("clothes","clothes_1");
            }
        });
        clothes_2 = view2.findViewById(R.id.clothes_2);
        clothes_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("clothes","clothes_2");
            }
        });


        //记录下装选项
        View view3 = View.inflate( this, (R.layout.layout_buttom), null);
        buttom_1 = view3.findViewById(R.id.buttom_1);
        buttom_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("buttom","buttom_1");
            }
        });
        buttom_2 = view3.findViewById(R.id.buttom_2);
        buttom_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("buttom","buttom_2");
            }
        });


        //记录鞋子选项
        View view4 = View.inflate( this, (R.layout.layout_shoes), null);
        shoe_1 = view4.findViewById(R.id.shoe_1);
        shoe_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("shoe","shoe_1");
            }
        });
        shoe_2 = view4.findViewById(R.id.shoe_2);
        shoe_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("shoe","shoe_2");
            }
        });

        //记录配饰选项
        View view5 = View.inflate( this, (R.layout.layout_accessorie), null);
        acc_1 = view5.findViewById(R.id.acc_1);
        acc_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("acc","acc_1");
            }
        });
        acc_2 = view5.findViewById(R.id.acc_2);
        acc_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundleItem.putString("acc","acc_2");
            }
        });

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                点击“生成形象”跳转到展示搭配界面
                Intent intent = new Intent(MainActivity.this, ShowMatchActivity.class);
                bundleItem.putInt("number",number);
                intent.putExtras(bundleItem);
                startActivity(intent);
            }
        });

    }

    public void initView() {
        myviewpager = (ViewPager) this.findViewById(R.id.myviewpager);

        btn_first =  this.findViewById(R.id.btn_first);
        btn_second =  this.findViewById(R.id.btn_second);
        btn_third = this.findViewById(R.id.btn_third);
        btn_four = this.findViewById(R.id.btn_four);
        btn_fifth =  this.findViewById(R.id.btn_fifth);
        btnArgs = new Button[]{btn_first, btn_second, btn_third, btn_four, btn_fifth};

        cursor = this.findViewById(R.id.cursor_btn);
        cursor.setBackgroundColor(Color.parseColor("#3399dd"));

        myviewpager.addOnPageChangeListener((ViewPager.OnPageChangeListener) this);
        btn_first.setOnClickListener((View.OnClickListener) this);
        btn_second.setOnClickListener((View.OnClickListener) this);
        btn_third.setOnClickListener((View.OnClickListener) this);
        btn_four.setOnClickListener((View.OnClickListener) this);
        btn_fifth.setOnClickListener((View.OnClickListener) this);

        fragments = new ArrayList<>();
        hairFragment = new HairFragment();
        fragments.add(hairFragment);

        clothesFragment = new ClothesFragment();
        fragments.add(clothesFragment);

        buttomFragment = new ButtomFragment();
        fragments.add(buttomFragment);

        shoesFragment = new ShoesFragment();
        fragments.add(shoesFragment);

        accessorieFragment = new AccessorieFragment();
        fragments.add(accessorieFragment);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        myviewpager.setAdapter(adapter);

        resetButtonColor();
        btn_first.setTextColor(Color.parseColor("#3399dd"));

    }

    //重置所有按钮的颜色
    public void resetButtonColor() {
        btn_first.setBackgroundColor(Color.WHITE);
        btn_second.setBackgroundColor(Color.WHITE);
        btn_third.setBackgroundColor(Color.WHITE);
        btn_four.setBackgroundColor(Color.WHITE);
        btn_fifth.setBackgroundColor(Color.WHITE);
        btn_first.setTextColor(Color.parseColor("#3399dd"));
        btn_second.setTextColor(Color.parseColor("#3399dd"));
        btn_third.setTextColor(Color.parseColor("#3399dd"));
        btn_four.setTextColor(Color.parseColor("#3399dd"));
        btn_fifth.setTextColor(Color.parseColor("#3399dd"));
    }


    public void onClick(View whichbtn) {
        // TODO Auto-generated method stub

        switch (whichbtn.getId()) {
            case R.id.btn_first:
                myviewpager.setCurrentItem(0);
                cursorAnim(0);

                break;
            case R.id.btn_second:
                myviewpager.setCurrentItem(1);
                cursorAnim(1);

                break;
            case R.id.btn_third:
                myviewpager.setCurrentItem(2);
                cursorAnim(2);

                break;
            case R.id.btn_four:
                myviewpager.setCurrentItem(3);
                cursorAnim(3);

                break;
            case R.id.btn_fifth:
                myviewpager.setCurrentItem(4);
                cursorAnim(4);

                break;
            case R.id.go:

        }
    }


    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }


    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }


    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        if (widthArgs == null) {
            widthArgs = new int[]{btn_first.getWidth(),
                    btn_second.getWidth(),
                    btn_third.getWidth(),
                    btn_four.getWidth(),
                    btn_fifth.getWidth()};
        }
        //每次滑动首先重置所有按钮的颜色
        resetButtonColor();
        //将滑动到的当前按钮颜色设置为蓝色
        btnArgs[arg0].setTextColor(Color.WHITE);
        btnArgs[arg0].setBackgroundColor(Color.parseColor("#A4D3EE"));
        cursorAnim(arg0);
    }

    //指示器的跳转，传入当前所处的页面的下标
    public void cursorAnim(int curItem) {
        //每次调用，就将指示器的横坐标设置为0，即开始的位置
        cursorX = 0;
        //再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
        //减去边距*2，以对齐标题栏文字
        lp.width = widthArgs[curItem] - btnArgs[0].getPaddingLeft() * 2;
        cursor.setLayoutParams(lp);
        //循环获取当前页之前的所有页面的宽度
        for (int i = 0; i < curItem; i++) {
            cursorX = cursorX + btnArgs[i].getWidth();
        }
        //再加上当前页面的左边距，即为指示器当前应处的位置
        cursor.setX(cursorX + btnArgs[curItem].getPaddingLeft());

    }

    public void setData(String t){
        btn_go.setText(t);
    }


}
