package com.example.zy.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class    TabFragment3 extends Fragment {
    private TextView tv_name;
    private TextView tv_user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//我
        View view=inflater.inflate(R.layout.fragment_me,container,false);
        tv_name= (TextView) view.findViewById(R.id.tv_name);
        tv_user= (TextView) view.findViewById(R.id.tv_fxid);
        SharedPreferences sp=getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        tv_name.setText(sp.getString("name",""));
        tv_user.setText("微信号:"+sp.getString("user1",""));
        return view;
    }

}
