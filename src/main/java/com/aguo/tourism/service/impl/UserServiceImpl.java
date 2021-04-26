package com.aguo.tourism.service.impl;

import com.aguo.tourism.dao.UserDao;
import com.aguo.tourism.dao.impl.UserDaoImpl;
import com.aguo.tourism.domain.User;
import com.aguo.tourism.service.UserService;
import com.aguo.tourism.utils.MailUtils;
import com.aguo.tourism.utils.UuidUtil;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/21 上午10:58
 */
public class UserServiceImpl implements UserService {
    private UserDao ud = new UserDaoImpl();

    @Override
    public User loginUser(String username, String password) {
        User user = ud.loginUser(username, password);
        return user;
    }

    @Override
    public boolean userAdd(User user) {
        boolean flag = false;
        flag = ud.userNameCheck(user.getUsername());
        if (flag){
            //首先保存信息之前设置激活状态为N
            user.setStatus("N");

            //生成全球唯一的UUID
            String uuid = UuidUtil.getUuid();
            //将UUID存入code中
            user.setCode(uuid);

            //给用户发激活邮件
            String code = "<a href=http://localhost:8080/aguo/activeUserServlet?code="+uuid+">黑马旅游网邮箱激活</a>";

            MailUtils.sendMail(user.getEmail(),code,"黑马旅游网邮箱激活");
            flag = ud.userAdd(user);
        }
        return flag;
    }

    @Override
    public boolean active(String code) {
        boolean flag = ud.checkCode(code);
        if (flag){
            //确实存在激活码
            flag = ud.updateStatus(code);
            return flag;
        }
        return false;
    }

    @Override
    public void checkEmail(User user) {
        boolean flag = ud.checkCode(user.getCode());
        if (flag){
            //给用户发激活邮件
            String code = "<a href=http://localhost:8080/aguo/activeUserServlet?code="+user.getCode()+">黑马旅游网邮箱激活</a>";

            MailUtils.sendMail(user.getEmail(),code,"黑马旅游网邮箱激活");
        }
    }
}
