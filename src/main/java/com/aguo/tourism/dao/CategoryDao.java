package com.aguo.tourism.dao;

import com.aguo.tourism.domain.Category;

import java.util.List;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/27 下午8:43
 */
public interface CategoryDao {

    /**
     * 导航栏查询
     * @return
     */
    public List<Category> getCategory();
}
