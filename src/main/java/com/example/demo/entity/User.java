package com.example.demo.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String user_name;    //用户名
    private String password;    //密码
    private String role_name;   //角色

    public User(){
    }

    public User(int id, String username, String password, String role_name) {
        this.id = id;
        this.user_name = username;
        this.password = password;
        this.role_name = role_name;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    enum Role{
        ADMINISTRATOR("管理员"), USER("普通用户");

        private final String role_name;
        Role(String role_name)
        {
            this.role_name = role_name;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", role_name='" + role_name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(Role.ADMINISTRATOR.role_name);
        System.out.println(Role.USER.role_name);
    }
}