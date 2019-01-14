package com.zhibei.faseJson;

import com.alibaba.fastjson.JSON;
import com.zhibei.bean.DataObj;
import com.zhibei.bean.SqlBean;
import java.util.HashMap;

public class ObjToJson {
    private static SqlBean sqlBean = new SqlBean();
    private static HashMap<String, DataObj> map = new HashMap<String, DataObj>();
    static {
        //用类似的数据对JSON进行预热处理
        String s = JSON.toJSONString(new SqlBean());
        JSON.parse(s);
        sqlBean.setFlag(1);
        sqlBean.setSql("insert into user values(\"aaaaa\")");
        DataObj dataObj = new DataObj();
        map.put("aaaaaa0",dataObj);
        sqlBean.setData(map);

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String json = JSON.toJSONString(sqlBean);
        System.out.println("used time ["+(System.currentTimeMillis()-start)+"]");
        JSON.parse(json);
        System.out.println("used time ["+(System.currentTimeMillis()-start)+"]");
    }
}
