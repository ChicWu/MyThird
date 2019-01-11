package com.zhibei.bean;

import java.util.HashMap;

public class SqlBean {
    private String sql = null;

    private HashMap<String, DataObj> data = new HashMap<String, DataObj>();

    private Integer flag = 0;
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }


    public HashMap<String, DataObj> getData() {
        return data;
    }

    public void setData(HashMap<String, DataObj> data) {
        this.data = data;
    }


    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}
