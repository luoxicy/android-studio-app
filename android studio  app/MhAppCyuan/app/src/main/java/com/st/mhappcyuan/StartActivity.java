package com.st.mhappcyuan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private View view1, view2, view3; //定义对应的启动页三个页面对象
    private ViewPager viewPager;  //定义对应的viewPager
    private List<View> viewList;//定义view数组列表

    private List<View> dotList;//定义点view数组列表
    private int oldPosition = 0;  // 定义变量，记录上一次点的位置
    private int currentItem;   // 定义变量，记录当前页面

    Button btnSkip;///////////////////////////////////////

    boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //设置启动页
        setViewPager();

        //调用按钮事件
        setEvent();////////////////////////////////////

        //调用首次启动显示功能
        isFirst();

    }

    private void isFirst() {

        SharedPreferences sharedPreferences = getSharedPreferences("First",MODE_PRIVATE);
        isFirst = sharedPreferences.getBoolean("isFirst",true);
        if (isFirst)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirst",false);
            editor.commit();
        }else{
            Intent intent=new Intent(StartActivity.this, com.st.mhappcyuan.MainActivity.class);
            startActivity(intent);
            finish();//销毁当前页面

        }
    }


    private void setEvent() {

        btnSkip=view3.findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(StartActivity.this, com.st.mhappcyuan.MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    private void setViewPager() {

        //第一步：初始化控件对象和变量
        //初始化viewPager对象，绑定控件
        viewPager = (ViewPager) findViewById(R.id.viewpager_start);

        //初始化layout对象
        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout_start1, null);
        view2 = inflater.inflate(R.layout.layout_start2,null);
        view3 = inflater.inflate(R.layout.layout_start3, null);

        // 实例化view列表对象，并将要分页显示的View装入数组中
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        //实例化点View列表对象
        dotList = new ArrayList<View>();
        dotList.add(findViewById(R.id.dot_1));
        dotList.add(findViewById(R.id.dot_2));
        dotList.add(findViewById(R.id.dot_3));

        dotList.get(0).setBackgroundResource(R.drawable.dot_normal);


        //第二步：实例化适配器对象
        com.st.mhappcyuan.MyPageAdapter adapter=new com.st.mhappcyuan.MyPageAdapter(viewList);

        //第三步：加载适配器
        viewPager.setAdapter(adapter);

        //第四步：设置页面变化事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                dotList.get(oldPosition).setBackgroundResource(R.drawable.dot_foucsed);
                dotList.get(position).setBackgroundResource(R.drawable.dot_normal);
                oldPosition=position;
                currentItem=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }


}
