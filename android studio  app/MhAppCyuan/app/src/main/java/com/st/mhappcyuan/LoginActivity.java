package com.st.mhappcyuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText lgname;
    EditText lgpwd;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lgname=findViewById(R.id.log_username);//获取输入的账号
        lgpwd=findViewById(R.id.log_password);//获取输入的密码
        login=findViewById(R.id.log_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    public void Login(){

        //储存从输入文本框获取到的内容
        String user = lgname.getText().toString().trim();
        String pwd = lgpwd.getText().toString().trim();

        //进行判断
        if(user.equals("") || pwd.equals("")){
            Toast.makeText(this, " 用户名与密码不能为空", Toast.LENGTH_SHORT).show();

        }else if (user.equals("admin") && pwd.equals("12345")){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, " 用户名或密码输入错误，登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}