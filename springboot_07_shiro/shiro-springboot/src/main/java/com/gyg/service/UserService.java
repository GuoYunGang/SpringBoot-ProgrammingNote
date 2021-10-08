package com.gyg.service;

import com.gyg.pojo.User;

/**
 * 用户接口
 */
public interface UserService {
    public User queryUserByName(String username);
}
