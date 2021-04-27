package com.aguo.tourism.domain;

/**
 * @Author Code Fruit
 * @Email 1260839205@qq.com
 * @Date 2021/4/27 下午8:44
 */
public class Category {
    private int cid;  //编号
    private String cname;  //导航栏名称

    public Category() {
    }

    public Category(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
