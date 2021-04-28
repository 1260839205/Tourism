package com.aguo.tourism.dao.impl;

import com.aguo.tourism.dao.CategoryDao;
import com.aguo.tourism.domain.Category;
import com.aguo.tourism.utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/27 下午8:43
 */
public class CategoryDaoImpl implements CategoryDao {
    //创建JdbcTemplate对象
    JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public List<Category> getCategory() {
        //编写sql语句
        String sql = "select * from tab_category order by cid DESC ";

        //执行sql语句
        List<Category> category = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));


        return category;
    }
}
