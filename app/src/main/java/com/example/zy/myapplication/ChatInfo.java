package com.example.zy.myapplication;

/**
 * Created by W on 2019/1/3.
 */

public class ChatInfo {
    private String name;
    private  String chat;
    private String time;

    public ChatInfo(String name, String chat, String time) {
        this.name = name;
        this.chat = chat;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
