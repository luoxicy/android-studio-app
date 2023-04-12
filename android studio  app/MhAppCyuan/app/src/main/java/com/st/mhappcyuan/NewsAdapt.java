package com.st.mhappcyuan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NewsAdapt extends ArrayAdapter<News> {
    int resource;
    public NewsAdapt(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //获取数据
        News news=getItem(position);

        //获取模板
        View view= LayoutInflater.from(getContext()).inflate(resource,null);

        //模板和数据匹配
        ImageView imageNews=view.findViewById(R.id.image_news);
        TextView textTitle=view.findViewById(R.id.text_title);
        TextView textAuthor=view.findViewById(R.id.text_author);
        TextView textDate=view.findViewById(R.id.text_date);
        TextView textView=view.findViewById(R.id.text_view);

        textTitle.setText(news.getTitle());
        textAuthor.setText(news.getAuthor());
        textDate.setText(news.getDate());
        textView.setText(news.getView());


        Bitmap bitmap= BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/"+news.getImage()));
        imageNews.setImageBitmap(bitmap);

        //返回匹配后的效果
        return view;

    }
}
