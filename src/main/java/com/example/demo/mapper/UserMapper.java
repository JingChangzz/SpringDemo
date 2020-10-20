package com.example.demo.mapper;

import com.example.demo.entity.User;

public interface UserMapper {
    // 对应xml映射文件元素的ID
    User getUserByUsername(String username);
    boolean delUserByUsername(String username);
}
