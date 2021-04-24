package com.aguo.tourism.dao;

import com.aguo.tourism.domain.User;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/20 下午9:14
 */
public interface UserDao {

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    public User loginUser(String username,String password);

    public boolean userAdd(User user);

    public boolean userNameCheck(String username);
}
