package com.st.mhappcyuan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {

    View view;

    int arrImages[];
    List<View> listImages;

    ViewPager viewPager;

    //设置文字的变量和控件
    String arrText[];
    TextView textViewPager;
    int oldPosition=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        //调用轮播图方法
        banner();

        //调用网格方法
        setGridView();

        //set
//        setIntroduce();

        //启动到计时
        handler.sendEmptyMessageDelayed(0,3000);

        return view;
    }



    private void setGridView() {

        //1.设置数据
        int arrGvImage[]={R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5,R.drawable.icon6,R.drawable.icon7,R.drawable.icon8,R.drawable.icon9};
        String arrGvText[]={"学院简介","院系介绍","创新创业","教学成果","实训基地","学生就业","国际交流","校园风采","联系我们"};
        List<Map<String,Object>> listMap=new ArrayList<>();
        for(int i=0;i<arrGvImage.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("image",arrGvImage[i]);
            map.put("text",arrGvText[i]);
            listMap.add(map);
        }

        //2.设置适配器
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),listMap,R.layout.item_home,new String[]{"image","text"},new int[]{R.id.image_gvhome,R.id.text_gvhome});

        //3.加载适配器
        MyGridView gvHome=view.findViewById(R.id.gv_home);
        gvHome.setAdapter(adapter);

        gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://学院简介
                        Intent intent1=new Intent(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse("http://www.sicp.edu.cn/html/781/"));
                        startActivity(intent1);
                        break;
                    case 1://院系介绍
                        Intent intent2=new Intent(Intent.ACTION_VIEW);
                        intent2.setData(Uri.parse("http://xxgk.sicp.edu.cn/23708/"));
                        startActivity(intent2);
                        break;
                    case 2://创新创业
                        Intent intent3=new Intent(Intent.ACTION_VIEW);
                        intent3.setData(Uri.parse("http://www.sicp.edu.cn/"));
                        startActivity(intent3);
                        break;
                    case 3://教学成果
                        Intent intent4=new Intent(Intent.ACTION_VIEW);
                        intent4.setData(Uri.parse("http://xxgk.sicp.edu.cn/23700/"));
                        startActivity(intent4);
                        break;
                    case 4://实训基地
                        Intent intent5=new Intent(Intent.ACTION_VIEW);
                        intent5.setData(Uri.parse("http://www.sicp.edu.cn/html/828/"));
                        startActivity(intent5);
                        break;
                    case 5://学生就业
                        Intent intent6=new Intent(Intent.ACTION_VIEW);
                        intent6.setData(Uri.parse("http://zhaosheng.sicp.edu.cn/"));
                        startActivity(intent6);
                        break;
                    case 6://国际交流
                        Intent intent7=new Intent(Intent.ACTION_VIEW);
                        intent7.setData(Uri.parse("http://gjjl.sicp.edu.cn/"));
                        startActivity(intent7);
                        break;
                    case 7://校园风采
                        Intent intent8=new Intent(Intent.ACTION_VIEW);
                        intent8.setData(Uri.parse("http://www.sicp.edu.cn/html/898/"));
                        startActivity(intent8);
                        break;
                    case 8://联系我们
                        Intent intent9=new Intent(Intent.ACTION_VIEW);
                        intent9.setData(Uri.parse("http://www.sicp.edu.cn/html/783/"));
                        startActivity(intent9);
                        break;
                }
            }
        });

    }

    private void banner() {

        //1.设置轮播内容
        arrImages=new int[]{R.drawable.turn1,R.drawable.turn2,R.drawable.turn3,R.drawable.turn4};
        listImages=new ArrayList<>();

        for(int i=0;i<arrImages.length;i++){

            ImageView imageView=new ImageView(getActivity());
            imageView.setBackgroundResource(arrImages[i]);
            listImages.add(imageView);
        }

        //2.设置适配器
        com.st.mhappcyuan.MyPageAdapter adapter=new com.st.mhappcyuan.MyPageAdapter(listImages);

        //3.加载适配器
        viewPager=view.findViewById(R.id.viewpager_home);
        viewPager.setAdapter(adapter);

        //设置文字联动
        arrText=new String[]{"宿舍楼","广场","小路","青浦校区"};
        textViewPager=view.findViewById(R.id.text_viewpager);
        textViewPager.setText(arrText[oldPosition]);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                textViewPager.setText(arrText[position]);
                oldPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });






    }

    private void setIntroduce() {

//        Intent intent=new Intent(getActivity(),HomeDetailActivity.class);
//        startActivity(intent);

    }

    Handler handler=new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if(msg.what==0){
                //倒计时需要完成的功能
                viewPager.setCurrentItem(  ( viewPager.getCurrentItem()+1  ) % listImages.size() );

                //再次启动倒计时
                handler.sendEmptyMessageDelayed(0,3000);

            }

        }
    };


}
