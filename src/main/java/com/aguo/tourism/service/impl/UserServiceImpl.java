package com.aguo.tourism.service.impl;

import com.aguo.tourism.dao.UserDao;
import com.aguo.tourism.dao.impl.UserDaoImpl;
import com.aguo.tourism.domain.User;
import com.aguo.tourism.service.UserService;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/21 上午10:58
 */
public class UserServiceImpl implements UserService {
    private UserDao ud = new UserDaoImpl();

    @Override
    public boolean loginUser(String username, String password) {
        User user = ud.loginUser(username, password);
        if (user != null){
            return true;
        }
        return false;
    }
}
