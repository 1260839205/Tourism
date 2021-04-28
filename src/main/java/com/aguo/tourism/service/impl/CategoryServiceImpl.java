package com.aguo.tourism.service.impl;

import com.aguo.tourism.dao.CategoryDao;
import com.aguo.tourism.dao.impl.CategoryDaoImpl;
import com.aguo.tourism.domain.Category;
import com.aguo.tourism.service.CategoryService;

import java.util.List;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/28 上午9:59
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao cd = new CategoryDaoImpl();
    @Override
    public List<Category> getCategory() {
        return cd.getCategory();
    }
}
