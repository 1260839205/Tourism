package com.aguo.tourism.dao.impl;

import com.aguo.tourism.dao.RouteDao;
import com.aguo.tourism.utils.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/28 下午8:22
 */
public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
    @Override
    public int getCount(int cid) {
        String sql = "select count(*) from tab_route where cid = ? ";
        return template.queryForObject(sql,Integer.class,cid);
    }
}
