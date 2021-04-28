package com.aguo.tourism.service.impl;

import com.aguo.tourism.dao.CategoryDao;
import com.aguo.tourism.dao.impl.CategoryDaoImpl;
import com.aguo.tourism.domain.Category;
import com.aguo.tourism.service.CategoryService;
import com.aguo.tourism.utils.JedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/28 上午9:59
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao cd = new CategoryDaoImpl();
    Jedis jedis = JedisUtils.getJedisPool();
    @Override
    public String getCategory() {
        if (jedis.get("category") == null){
            ObjectMapper om = new ObjectMapper();
            try {
                jedis.set("category",om.writeValueAsString(cd.getCategory()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return jedis.get("category");
    }
}
