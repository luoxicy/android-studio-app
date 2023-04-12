package com.st.mhappcyuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;

public class DialogActivity extends AppCompatActivity {

    EditText etComment;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


        //初始化界面
        initView();


    }

    private void initView() {
        etComment=findViewById(R.id.et_comment);
        btnSubmit=findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //原生写法
                //获取留言数据
                String content=etComment.getText().toString().trim();

                if (content.equals("")|| content==null){
                    Toast.makeText(DialogActivity.this,"留言不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                //获取日期
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String date=sdf.format(new Date());
                //1.创建数据库和数据表
                DbHelper dbHelper=new DbHelper(DialogActivity.this);
                //2.设置数据库权限,实例化数据库对象
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                //设置新增数据
                ContentValues values=new ContentValues();
                values.put("content",content);
                values.put("date",date);
                //将数据添加到数据库
                db.insert("message",null,values);
                //弹出提示框
                Toast.makeText(DialogActivity.this,"留言成功",Toast.LENGTH_LONG).show();
                //关闭当前窗口
                finish();

            }
        });

    }

    public void close(View view) {
        finish();
    }
}