package com.example.zy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity_Login extends AppCompatActivity implements View.OnClickListener {
    private Button btn1_register, btn_login;
    private EditText et_usertel, et_password;
    final OkHttpClient client = new OkHttpClient();
    private Gson gson;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息1", ReturnMessage);
//               JsonObject jsonObject=org.json.JSONObject.fromObject("{\"name\":\"json\",\"bool\":true,\"int\":1}");

                gson = new Gson();
                UserInfo userInfo = gson.fromJson(ReturnMessage, UserInfo.class);
                Log.i("获取的返回信息2", userInfo.toString());

                if (userInfo.getStatus().equals("登陆成功")) {

                    Toast.makeText(MainActivity_Login.this, "欢迎回来[ "+userInfo.getName()+" ]", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity_Login.this, MainActivity.class);
                    SharedPreferences sp=getSharedPreferences("data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("user",userInfo.getUser());
                    edit.putString("user1",et_usertel.getText().toString());
                    edit.putString("name",userInfo.getName());
                    edit.putString("password",et_password.getText().toString());
                    edit.commit();




                    //                    intent.putExtra("username",userInfo.getName());
//                    intent.putExtra("usernum",userInfo.getUser());
//                    Toast.makeText(MainActivity_Login.this, "name:"+userInfo.getName()+"user"+userInfo.getUser(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity_Login.this, "登陆失败！", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_usertel = (EditText) findViewById(R.id.et_usertel);
        et_password = (EditText) findViewById(R.id.et_password);
        btn1_register = (Button) findViewById(R.id.btn1_register);
        btn_login = (Button) findViewById(R.id.btn1_login);

        btn1_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1_register:
                Intent intent = new Intent(MainActivity_Login.this, MainActivity_Register.class);
                startActivity(intent);
                break;
            case R.id.btn1_login:
                login();
                break;

        }
    }

    //登录
    private void login() {
        final String username = et_usertel.getText().toString().trim();
        final String password = et_password.getText().toString().trim();
        //        final String url="http://123.207.85.214/chat/login.php";
        //验证是否为空
        if (TextUtils.isEmpty(username)) {

            Toast.makeText(MainActivity_Login.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity_Login.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
    //请求
        postRequest(username, password);


        //https://www.cnblogs.com/liunanjava/p/5326176.html
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils httpUtils = new HttpUtils();
                String user = httpUtils.bolwingJson(username, password);


                try {
                    final String result = httpUtils.login(url, user);
                    Log.d("ffff", "结果:" + result);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();*/


    }

    private void postRequest(String username, String password) {

        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("user", username)
                .add("password", password)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://123.207.85.214/chat/login.php")
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        handler.obtainMessage(1, response.body().string()).sendToTarget();

                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}
