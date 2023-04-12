package com.st.mhappcyuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.collection.ArraySet;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.OkHeaders;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    View view;
    List<News> listNews=new ArrayList<>();

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_news, container, false);

        //设置动态列表(本地json)
        setListViewUseLocalJson();

        //设置动态列表(网络json-原生)
//        setListViewUseNetJson();

        //设置动态列表(网络json-Okhttp)
//        setListViewOkhttpJson();

        return view;
    }

    private void setListViewOkhttpJson() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url("http://192.168.2.203:3000/news.json")
                        .build();
                try {
                    Response response=client.newCall(request).execute();
                    String data=response.body().string();

                    Message message=new Message();
                    message.obj=data;
                    handler.sendMessage(message);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        ).start();
    }

    private void setListViewUseNetJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String path="http://192.168.2.203:3000/news.json";
                    URL url=new URL(path);
                    HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(1500);
                    connection.setDoInput(true);
                    connection.setDoOutput(false);

                    int code=connection.getResponseCode();

                    if (code==200){

                        InputStreamReader isr=new InputStreamReader(connection.getInputStream());
                        BufferedReader br=new BufferedReader(isr);
                        StringBuffer sb=new StringBuffer();
                        String line="";
                        while(    (line= br.readLine() ) !=null      ){
                            sb.append(line);
                        }
                        br.close();
                        isr.close();

                        Message message=new Message();
                        message.obj=sb.toString();
                        handler.sendMessage(message);
                    }
                }catch (MalformedURLException e) {
                    e.printStackTrace();
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

            //1.解析网络的json数据
            jsonAnalysis(msg.obj.toString());

            //2.设置适配器
            NewsAdapt adapter=new NewsAdapt(getActivity(),R.layout.item_news,listNews);
            //3.加载适配器
            ListView lvNews=view.findViewById(R.id.lv_news);
            lvNews.setAdapter(adapter);


        }
    };


    private void setListViewUseLocalJson() {

        //1.设置数据
        //第一步：读取json文件的内容，并转换成字符串
        try {
            InputStreamReader isr=new InputStreamReader(getResources().openRawResource(R.raw.news),"UTF-8");
            BufferedReader br=new BufferedReader(isr);
            StringBuffer sb=new StringBuffer();
            String line="";
            while(    (line= br.readLine() ) !=null   ){
                sb.append(line);
            }
            br.close();
            isr.close();
            //将json字符串进行解析
            jsonAnalysis(sb.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.设置适配器
        NewsAdapt adapter=new NewsAdapt(getActivity(),R.layout.item_news,listNews);
        //3.加载适配器
        ListView lvNews=view.findViewById(R.id.lv_news);
        lvNews.setAdapter(adapter);


        //4.设置列表功能
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),NewsDetailActivity.class);
                intent.putExtra("news",listNews.get(position));
                startActivity(intent);
            }
        });


    }
    private void jsonAnalysis(String json){
        try {
            JSONObject jsonObject=new JSONObject(json);

            JSONArray jsonArray=jsonObject.getJSONArray("data");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject dataObject=jsonArray.getJSONObject(i);
                String id=dataObject.getString("id");
                String image=dataObject.getString("image");
                String title=dataObject.getString("title");
                String date=dataObject.getString("date");
                String author=dataObject.getString("author");
                String view=dataObject.getString("view");
                String content=dataObject.getString("content");

                News news=new News(id,image,title,date,author,view,content);

                listNews.add(news);
            }


        }catch (JSONException e){
            e.printStackTrace();
        }

    }




}
