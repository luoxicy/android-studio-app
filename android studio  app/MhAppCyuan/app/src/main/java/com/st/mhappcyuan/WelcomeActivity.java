package com.st.mhappcyuan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class WelcomeActivity extends AppCompatActivity {

    TextView textTime,textSkip;
    int count=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //初始化控件
        init();

        //启动到计时
        handler.sendEmptyMessageDelayed(0,1000);


    }

    private void init() {

        textTime=findViewById(R.id.text_time);
        textSkip=findViewById(R.id.text_skip);
    }

    Handler handler=new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if(msg.what==0){
                //倒计时需要完成的功能
                textTime.setText("倒计时 "+count+"S");
                count--;
                if(count==0){
                    Intent intent=new Intent( WelcomeActivity.this, com.st.mhappcyuan.MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                //再次启动倒计时
                handler.sendEmptyMessageDelayed(0,1000);

            }

        }
    };


}
