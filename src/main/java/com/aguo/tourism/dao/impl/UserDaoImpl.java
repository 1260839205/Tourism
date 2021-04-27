package com.aguo.tourism.dao.impl;

import com.aguo.tourism.dao.UserDao;
import com.aguo.tourism.domain.User;
import com.aguo.tourism.utils.JdbcUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
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

    /**
     * 注册账号
     * @param user
     * @return
     */
    @Override
    public boolean userAdd(User user) {
        String sql = "insert into tab_user values (null,?,?,?,?,?,?,?,?,?)";


        int update = template.update(sql, user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex() ,user.getTelephone(), user.getEmail(),user.getStatus(),user.getCode());

        if (update > 0){
            return true;
        }
        return false;
    }

    /**
     * 验证用户名库中是否存在
     * @param username
     * @return
     */
    @Override
    public boolean userNameCheck(String username) {
        String sql = "select * from tab_user where username = ? ";
        try{
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            return !(user != null);
        }catch (IncorrectResultSetColumnCountException e){
            e.printStackTrace();
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 验证激活码
     * @param code
     * @return
     */
    @Override
    public boolean checkCode(String code) {
        User user = null;
        try {
            //定义sql
            String sql = "select * from tab_user where code = ? ";
            //调用方法查询
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (IncorrectResultSetColumnCountException e){
            e.printStackTrace();
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return (user != null);
    }


    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean updateStatus(String code) {
        String sql = "update tab_user set status = 'Y' where code = ? ";
        int update = template.update(sql, code);
        if (update > 0 ){
            return true;
        }
        return false;
    }
}
