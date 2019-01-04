package com.example.zy.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;




public class TabFragment1 extends Fragment {
    private EditText content;
    private Button send;
    private ListView chat_lv;
    ArrayList<ChatInfo> chatlist;
    OkHttpClient client=new OkHttpClient();
    String ReturnMessage;

    SharedPreferences sp;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 5) {
                ReturnMessage = (String) msg.obj;

                Log.i("ddd", "handleMessage: "+ReturnMessage);
                showChat(ReturnMessage);
                chat_lv.setAdapter(new ChatAdapter(getContext(),chatlist));
            }
        }

        private void showChat(String returnMessage) {
            try {

                Log.i("ddd", "handleMessage: "+returnMessage);
                JSONArray chatArray =  new JSONArray(returnMessage);
                chatlist = new ArrayList<ChatInfo>();
                //倒序插入
                for (int i = chatArray.length()-1; i >= 0; i--) {
                    JSONObject chatInfoJson = chatArray.getJSONObject(i);
                    String  name= chatInfoJson.getString("name");
                    String time = chatInfoJson.getString("time");
                    String chat=chatInfoJson.getString("chat");

                    ChatInfo  chatinfo= new ChatInfo(name,chat,time);
                    chatlist.add(chatinfo);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    };

//显示聊天
    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 6) {
                ReturnMessage = (String) msg.obj;

                Log.i("ddd", "handleMessage: "+ReturnMessage);
                showChat(ReturnMessage);
                chat_lv.setAdapter(new ChatAdapter(getContext(),chatlist));
                content.setText("");
            }
        }

        private void showChat(String returnMessage) {
            try {

                Log.i("ddd", "handleMessage: "+returnMessage);
                JSONArray chatArray =  new JSONArray(returnMessage);
                chatlist = new ArrayList<ChatInfo>();
                for (int i = chatArray.length()-1; i >= 0; i--) {
                    JSONObject chatInfoJson = chatArray.getJSONObject(i);
                    String  name= chatInfoJson.getString("name");
                    String time = chatInfoJson.getString("time");
                    String chat=chatInfoJson.getString("chat");

                    ChatInfo  chatinfo= new ChatInfo(name,chat,time);
                    chatlist.add(chatinfo);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    };

//点击发送
    public  void post_Form(String url,final Handler handler){
        String user=sp.getString("user","");
        String password=sp.getString("password","");
        FormBody requestBody=new FormBody.Builder().add("user",user).add("password",password).add("chat",content.getText().toString()).build();
        Request request=new Request.Builder().url(url).addHeader("Content-Type", "application/json; charset=UTF-8")                             .post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {

            public void onResponse(Call arg0, Response arg1) throws IOException {
                if(arg1.isSuccessful()){
                    String returns = arg1.body().string();
                    Log.i("test","post表单请求成功,返回ok=returns:"+returns);
                    Message msg=new Message();
                    msg.what=5;
                    msg.obj=returns;
                    handler.sendMessage(msg);
                }
                else {
                    Log.i("test","post表单请求成功,返回NO");
                }
            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                Log.i("test","post表单请求失败");
            }
        });
        Log.i("test","post表单请求成功,方法完成");

    }

    //信息
    public  void post_Form1(String url,final Handler handler){

            String logis="1";
        FormBody requestBody=new FormBody.Builder().add("user",logis).build();
        Request request=new Request.Builder().url(url).addHeader("Content-Type", "application/json; charset=UTF-8")                             .post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {

            public void onResponse(Call arg0, Response arg1) throws IOException {
                if(arg1.isSuccessful()){
                    String returns = arg1.body().string();
                    Log.i("test","post表单请求成功,返回ok=returns:"+returns);
                    Message msg=new Message();
                    msg.what=6;
                    msg.obj=returns;
                    handler1.sendMessage(msg);
                }
                else {
                    Log.i("test","post表单请求成功,返回NO");
                }
            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                Log.i("test","post表单请求失败");
            }
        });
        Log.i("test","post表单请求成功,方法完成");

    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//聊天

        View view=inflater.inflate(R.layout.fragment_chat,container,false);
        sp=getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        content= (EditText) view.findViewById(R.id.lv_content);
        send= (Button) view.findViewById(R.id.send);
        chat_lv= (ListView) view.findViewById(R.id.lv_chat);

        post_Form1("http://123.207.85.214/chat/chat1.php",handler1);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                post_Form("http://123.207.85.214/chat/chat1.php",handler);

            }
        });
        return view;
    }







}
