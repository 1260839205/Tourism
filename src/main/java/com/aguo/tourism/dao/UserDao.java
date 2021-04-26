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

    /**
     * 注册账号
     * @param user
     * @return
     */
    public boolean userAdd(User user);

    /**
     * 验证用户名库中是否存在
     * @param username
     * @return
     */
    public boolean userNameCheck(String username);

    boolean checkCode(String code);

    boolean updateStatus(String code);
}
