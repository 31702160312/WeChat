package com.example.zy.myapplication;

/**
 * Created by Administrator on 2018/12/27.
 */

public class UserInfo {
    private String name;
    private String user;
    private String password;
    private String status;
    private String chat;

    public UserInfo(String name, String user) {
        this.name = name;
        this.user = user;
    }

    //注册
    class UserRegister {
        private String name;
        private int user;
        private int password;
        private String status;

        @Override
        public String toString() {
            return "UserRegister{" +
                    "name='" + name + '\'' +
                    ", user=" + user +
                    ", password=" + password +
                    ", status='" + status + '\'' +
                    '}';
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public int getPassword() {
            return password;
        }

        public void setPassword(int password) {
            this.password = password;
        }
    }
    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", chat='" + chat + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
