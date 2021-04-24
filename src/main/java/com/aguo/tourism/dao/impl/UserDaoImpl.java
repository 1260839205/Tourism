package com.aguo.tourism.dao.impl;

import com.aguo.tourism.dao.UserDao;
import com.aguo.tourism.domain.User;
import com.aguo.tourism.utils.JdbcUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/20 下午9:15
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());


    /**
     * 登陆方法实现
     * @param username
     * @param password
     * @return
     */
    @Override
    public User loginUser(String username, String password) {
        //1.SQL语句
        String sql = "select * from tab_user where username = ? and password = ? ";

        try {
            //调用方法查询数据库
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userAdd(User user) {
        String sql = "insert into tab_user values (null,?,?,?,?,?,?,?,'a','a')";

        System.out.println(user);
        int update = template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(),
                user.getName(), user.getTelephone(), user.getSex(), user.getBirthday());
        if (update > 0){
            return true;
        }
        return false;
    }
}
