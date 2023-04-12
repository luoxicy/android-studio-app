package com.st.mhappcyuan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MeFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_me, container, false);


        setListView();

        return view;
    }

    private void setListView() {
        //设置数据
        int arrImage[]={R.drawable.people,
                        R.drawable.phone,
                        R.drawable.web,
                        R.drawable.weixin,
                        R.drawable.mail,
                        R.drawable.copyright,
                        R.drawable.accoun};
        String arrText[]={"联系地址",
                          "联系电话",
                          "官方地址",
                          "官方微信",
                          "给我留言",
                          "当前版本",
                          "账户登录"};

        List<Map<String,Object>>listMap=new ArrayList<>();
        for(int i=0;i<arrImage.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("image",arrImage[i]);
            map.put("text",arrText[i]);
            listMap.add(map);
        }
        //设置适配器
        SimpleAdapter adapter=
                new SimpleAdapter(getActivity(),listMap,R.layout.item_me,
                        new String[]{"image","text"},
                        new int[]{R.id.img_me,R.id.txt_me});
        //加载适配器
        ListView lvMe=view.findViewById(R.id.lv_me);
        lvMe.setAdapter(adapter);

        //设置列表功能
        lvMe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0://联系地址
                        new AlertDialog.Builder(getActivity())
                                .setTitle("联系地址")
                                .setMessage("地址：\n上海市嘉定区外冈镇恒荣路200号（南校门）、冈峰公路68号（北校门）\n" +
                                        " \n上海市青浦区华新镇新凤北路565号（青浦校区）\n\n" +
                                        "电话：\n021-60675958 （总校区）\n021-60258299（青浦校区）\n\n" +
                                        "传真：59587076")
                                .setPositiveButton("确定",null)
                                .show();
                        break;
                    case 1://联系电话
                        Intent intent1=new Intent(Intent.ACTION_DIAL);
                        intent1.setData(Uri.parse("tel:10086"));
                        startActivity(intent1);
                        break;
                    case 2://官方网站
                        Intent intent2=new Intent(Intent.ACTION_VIEW);
                        intent2.setData(Uri.parse("http://www.sicp.edu.cn/"));
                        startActivity(intent2);

                        break;
                    case 3://官方微信
                        Dialog dialog3=new Dialog(getActivity());
                        dialog3.setContentView(R.layout.layout_code);
                        dialog3.show();
                        break;
                    case 4://给我留言
                        Intent intent4=new Intent(getActivity(),DialogActivity.class);
                        startActivity(intent4);
                        break;
                    case 5://当前版本
                        new AlertDialog.Builder(getActivity())
                                .setTitle("当前版本")
                                .setMessage("当前版本为v1.0版\n\n开发者：张三")
                                .setPositiveButton("确定",null)
                                .show();
                        break;
                    case 6://账户资料
                        Intent intent6=new Intent(getActivity(),LoginActivity.class);
                        startActivity(intent6);
                        break;

                }
            }
        });


    }


}
