package com.example.zy.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TabFragment2 extends Fragment {
    private EditText tv_name;
    private EditText tv_user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//我
        
        return inflater.inflate(R.layout.fragment_find,container,false);
    }

}
