package com.st.mhappcyuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NewsDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        //显示详细数据
        showNewDetail();
    }

    private void showNewDetail() {
        //接受参数
        Intent intent=getIntent();
        if(intent!=null){

            //获取传递过来的数据并显示到控件上
            TextView text_detail_title =findViewById(R.id.text_detail_title);
            TextView text_detail_content=findViewById(R.id.text_detail_content);
            ImageView image_detail=findViewById(R.id.image_detail);

        //接受一个对象，并将对象的相关属性值绑定到控件上
            News news=(News) intent.getSerializableExtra("news");

            Toast.makeText(this,news.getImage(),Toast.LENGTH_LONG).show();

            text_detail_title.setText(news.getTitle());
            text_detail_content.setText(news.getContent());


            Bitmap bitmap=
                    BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/"+news.getImage()));
            image_detail.setImageBitmap(bitmap);

        }


    }

    public void back(View view) {
        finish();
    }
}