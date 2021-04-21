package com.aguo.tourism.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/21 上午9:24
 */
public class JdbcUtils {
    //1.定义数据源
    private static DataSource ds;

    //编写静态代码块，加载配置文件
    static {
        //1.获取配置文件的输入流
        InputStream inputDruid = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");

        //2.创建读取数据的属性类
        Properties jdbcdruid = new Properties();

        try {
            //3.读取配置文件,抓一下IO异常
            jdbcdruid.load(inputDruid);

            //4.将读取的配置文件，写入进数据源,抓异常
            ds = DruidDataSourceFactory.createDataSource(jdbcdruid);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据源的方法
     * @return
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
