package com.aguo.tourism.service.impl;

import com.aguo.tourism.dao.RouteDao;
import com.aguo.tourism.dao.impl.RouteDaoImpl;
import com.aguo.tourism.service.RouteService;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/28 下午8:32
 */
public class RouteServiceImpl implements RouteService {
    RouteDao rd = new RouteDaoImpl();
    @Override
    public int getCount(int cid) {
        return rd.getCount(cid);
    }
}
