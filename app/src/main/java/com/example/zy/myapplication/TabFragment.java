package com.example.zy.myapplication;



import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TabFragment extends Fragment {
    OkHttpClient client=new OkHttpClient();
    ArrayList<UserInfo> userlist;
    private ListView lv_contact;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 6) {
                String ReturnMessage = (String) msg.obj;
                addUser(ReturnMessage);
                lv_contact.setAdapter(new UserAdapter(getContext(),userlist));
            }
        }
    };



        //GET异步方法
    public  void get_Y(String url,final Handler handler){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call arg0, Response arg1) throws IOException {
                if(arg1.isSuccessful()){
                    String returns = arg1.body().string();
                    Log.i("fragment","get异步请求成功，返回returns的值:"+ returns);
                    Message msg=new Message();
                    msg.what=6;
                    msg.obj=returns;
                    handler.sendMessage(msg);
                }
                else{
                    Log.i("fragment","get异步请求成功，返回NO:");
                }

            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                Log.i("test","get异步请求失败"+ arg0.toString());

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//通讯录
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        get_Y("http://123.207.85.214/chat/member.php",handler);
        lv_contact= (ListView) view.findViewById(R.id.contact_lv);
        return view;
    }
    private void addUser(String result) {
        try {
            JSONArray UserArray =  new JSONArray(result);
            userlist = new ArrayList<UserInfo>();
            for (int i = 0; i < UserArray.length(); i++) {
                JSONObject userInfoJson = UserArray.getJSONObject(i);
                String  name= userInfoJson.getString("name");
                String user = userInfoJson.getString("user");
                UserInfo userInfo = new UserInfo(name, user);
                userlist.add(userInfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
