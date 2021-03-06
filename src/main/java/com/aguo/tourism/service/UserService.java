package com.aguo.tourism.service;

import com.aguo.tourism.domain.User;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/21 上午10:57
 */
public interface UserService {

    public User loginUser(String username, String password);

    public boolean userAdd(User user);

    boolean active(String code);

    public void checkEmail(User user);
}
