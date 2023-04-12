package com.st.mhappcyuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment {

    View view;
    private List<String> TitleList;
    TabHost tabHost;

    List<String> titleList=new ArrayList<>();

    private View view1, view2, view3,view4,view5; //定义对应的启动页三个页面对象
    private ViewPager viewPager;  //定义对应的viewPager
    private List<View> viewList;//定义view数组列表
    List<Map<String,Object>> listMap=new ArrayList<>();

    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_notice, container, false);
        // 设置TabHost
        setTabHost();

        // 设置ViewPager
        setViewPager();

        //设置Tab
        setTab1();

        //设置Tab
        setTab2();

        //设置Tab
        setTab3();

        //设置Tab
        setTab4();

        //设置Tab
        setTab5();



        return view;
    }

    private void setTab2() {
        setListViewOkhttpJson();
    }

        private void setListViewOkhttpJson() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://192.168.2.203:3000/notice2.json")
                            .build();
                    try {
                        //连接网络请求
                        Response response=client.newCall(request).execute();
                        //获取网络数据
                        String data=response.body().string();
                        //解析网络的json
                        jsonAnalysis(data);

                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            ).start();
        }

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                //2.设置适配器
                SimpleAdapter adapter=new SimpleAdapter(getActivity(),listMap,
                        R.layout.item_notice2, new String[]{"image","title","date"},
                        new int[]{R.id.image_notice2,R.id.text_notice2_title,R.id.text_notice2_date});

                adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Object data, String textRepresentation) {
                        if(view instanceof ImageView && data instanceof Bitmap){

                            ImageView imageView=(ImageView) view;
                            imageView.setImageBitmap((Bitmap) data);
                            return true;

                        }
                        return false;
                    }
                });
                //3.加载适配器
                ListView lvNotice2=view2.findViewById(R.id.lv_notice2);
                lvNotice2.setAdapter(adapter);

                //4.
                lvNotice2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(listMap.get(position).get("url").toString()));
                        startActivity(intent);
                    }
                });

            }
        };

    //获取网络图片数据
    public Bitmap returnBitmap(String url){
        Bitmap bitmap=null;

        try {
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode()==200){

                InputStream is=conn.getInputStream();
                bitmap= BitmapFactory.decodeStream(is);

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return bitmap;
    }

        private void jsonAnalysis(String json){
            try {
                JSONObject jsonObject=new JSONObject(json);

                JSONArray jsonArray=jsonObject.getJSONArray("data");
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject dataObject=jsonArray.getJSONObject(i);

                    String id=dataObject.getString("id");
                    String image=dataObject.getString("image");
                    Bitmap bitmap=returnBitmap(image);
                    String title=dataObject.getString("title");
                    String date=dataObject.getString("date");
                    String url=dataObject.getString("url");

                    Map<String,Object> map=new HashMap<>();
                    map.put("id",id);
                    map.put("image",bitmap);
                    map.put("title",title);
                    map.put("date",date);
                    map.put("url",url);

                    listMap.add(map);
                }


            }catch (JSONException e){
                e.printStackTrace();
            }

        }

    private void setTab4() {
        //控件绑定
        WebView webView=view4.findViewById(R.id.webview4);
        //设置webView的URL
        webView.loadUrl("http://www.sicp.edu.cn/html/30/");
        //设置网页显示位置
        webView.scrollBy(100,1100);
    }
    private void setTab5() {
        //控件绑定
        WebView webView=view5.findViewById(R.id.webview5);
        //设置webView的URL
        webView.loadUrl("http://www.sicp.edu.cn/html/31/");
        //设置网页显示位置
        webView.scrollBy(100,1100);
    }

    private void setTab3() {
        //控件绑定
        WebView webView=view3.findViewById(R.id.webview3);
        //设置webView的URL
        webView.loadUrl("http://www.sicp.edu.cn/html/156/");
        //设置网页显示位置
        webView.scrollBy(100,1100);
    }

    private void setTab1() {
        //控件绑定
        WebView webView=view1.findViewById(R.id.webview1);
        //设置webView的URL
        webView.loadUrl("http://www.sicp.edu.cn/html/128/");
        //设置网页显示位置
        webView.scrollBy(100,100);
    }



    private void setViewPager() {

        //第一步：初始化控件对象和变量
        //初始化viewPager对象，绑定控件
        viewPager =view.findViewById(R.id.viewpager_notice);

        //初始化layout对象
        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout_notice1, null);
        view2 = inflater.inflate(R.layout.layout_notice2,null);
        view3 = inflater.inflate(R.layout.layout_notice3, null);
        view4 = inflater.inflate(R.layout.layout_notice4, null);
        view5 = inflater.inflate(R.layout.layout_notice5, null);

        // 实例化view列表对象，并将要分页显示的View装入数组中
        viewList = new ArrayList<View>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);





        //第二步：实例化适配器对象
        MyPageAdapter adapter=new MyPageAdapter(viewList);

        //第三步：加载适配器
        viewPager.setAdapter(adapter);

        //第四步：设置页面变化事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabHost.setCurrentTab(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

    private void setTabHost() {

        //初始化TabHost对象
        tabHost=view.findViewById(R.id.tabhost);
        tabHost.setup();

        //设置标题栏数据
        titleList.add("通知公告");
        titleList.add("文明建设");
        titleList.add("学生风采");
        titleList.add("社团活动");
        titleList.add("系部动态");

        //将标标题栏数据显示到tabhost中
        for(String title :titleList){
            tabHost.addTab(tabHost.newTabSpec(title).setIndicator(title,null).setContent(R.id.tab1));


        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                viewPager.setCurrentItem(titleList.indexOf(tabId));
            }
        });






    }
}
