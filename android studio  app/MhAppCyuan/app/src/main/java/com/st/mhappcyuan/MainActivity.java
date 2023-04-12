package com.st.mhappcyuan;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    //定义控件
    LinearLayout navHome,navNews,navNotice,navMe,layoutMain;
    ImageView imageHome,imageNews,imageNotice,imageMe;
    TextView textHome,textNews,textNotice,textMe,textTitle;

    HomeFragment homeFragment;
    NewsFragment newsFragment;
    NoticeFragment noticeFragment;
    MeFragment meFragment;

    Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        init();

        //导航事件
        navEvent();

        //默认激活首页导航
        navHome.callOnClick();

    }
    private void init() {

        navHome=findViewById(R.id.nav_home);
        navNews=findViewById(R.id.nav_news);
        navNotice=findViewById(R.id.nav_notice);
        navMe=findViewById(R.id.nav_me);
        layoutMain=findViewById(R.id.layout_main);

        imageHome=findViewById(R.id.image_home);
        imageNews=findViewById(R.id.image_news);
        imageNotice=findViewById(R.id.image_notice);
        imageMe=findViewById(R.id.image_me);

        textHome=findViewById(R.id.text_home);
        textNews=findViewById(R.id.text_news);
        textNotice=findViewById(R.id.text_notice);
        textMe=findViewById(R.id.text_me);
        textTitle=findViewById(R.id.text_title);

    }
    private void navEvent() {
        //首页
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改标题
                textTitle.setText("首页");
                //修改图片
                imageHome.setImageResource(R.drawable.home_active);
                imageNews.setImageResource(R.drawable.news);
                imageNotice.setImageResource(R.drawable.notice);
                imageMe.setImageResource(R.drawable.me);
                //修改文字颜色
                textHome.setTextColor(Color.RED);
                textNews.setTextColor(Color.GRAY);
                textNotice.setTextColor(Color.GRAY);
                textMe.setTextColor(Color.GRAY);
                //切换Fragment页面
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                    transaction.add(R.id.layout_main,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                if(currentFragment!=null && currentFragment!=homeFragment){
                    transaction.hide(currentFragment);
                }
                currentFragment=homeFragment;
                transaction.commit();



            }
        });

        //新闻
        navNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //修改标题
                textTitle.setText("新闻");

                //修改图片
                imageHome.setImageResource(R.drawable.home);
                imageNews.setImageResource(R.drawable.news_active);
                imageNotice.setImageResource(R.drawable.notice);
                imageMe.setImageResource(R.drawable.me);

                //修改文字颜色
                textHome.setTextColor(Color.GRAY);
                textNews.setTextColor(Color.RED);
                textNotice.setTextColor(Color.GRAY);
                textMe.setTextColor(Color.GRAY);

                //切换Fragment页面
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                if(newsFragment==null){
                    newsFragment=new NewsFragment();
                    transaction.add(R.id.layout_main,newsFragment);
                }else{
                    transaction.show(newsFragment);
                }
                if(currentFragment!=null && currentFragment!=newsFragment){
                    transaction.hide(currentFragment);
                }
                currentFragment=newsFragment;
                transaction.commit();


            }
        });

        //公告
        navNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //修改标题
                textTitle.setText("公告");

                //修改图片
                imageHome.setImageResource(R.drawable.home);
                imageNews.setImageResource(R.drawable.news);
                imageNotice.setImageResource(R.drawable.notice_active);
                imageMe.setImageResource(R.drawable.me);

                //修改文字颜色
                textHome.setTextColor(Color.GRAY);
                textNews.setTextColor(Color.GRAY);
                textNotice.setTextColor(Color.RED);
                textMe.setTextColor(Color.GRAY);//切换Fragment

                //切换Fragment页面
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                if(noticeFragment==null){
                    noticeFragment=new NoticeFragment();
                    transaction.add(R.id.layout_main,noticeFragment);
                }else{
                    transaction.show(noticeFragment);
                }
                if(currentFragment!=null && currentFragment!=noticeFragment){
                    transaction.hide(currentFragment);
                }
                currentFragment=noticeFragment;
                transaction.commit();



            }
        });

        //我的
        navMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //修改标题
                textTitle.setText("我的");

                //修改图片
                imageHome.setImageResource(R.drawable.home);
                imageNews.setImageResource(R.drawable.news);
                imageNotice.setImageResource(R.drawable.notice);
                imageMe.setImageResource(R.drawable.me_active);

                //修改文字颜色
                textHome.setTextColor(Color.GRAY);
                textNews.setTextColor(Color.GRAY);
                textNotice.setTextColor(Color.GRAY);
                textMe.setTextColor(Color.RED);

                //切换Fragment页面
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                if(meFragment==null){
                    meFragment=new MeFragment();
                    transaction.add(R.id.layout_main,meFragment);
                }else{
                    transaction.show(meFragment);
                }
                if(currentFragment!=null && currentFragment!=meFragment){
                    transaction.hide(currentFragment);
                }
                currentFragment=meFragment;
                transaction.commit();
            }
        });

    }
}
