package com.zhibei.bean;

import java.util.ArrayList;
import java.util.List;

public class

DataObj {
    private List<Object> col = new ArrayList<Object>();

    private Integer type = 0;


    public List<Object> getCol() {
        return col;
    }

    public void setCol(List<Object> col) {
        this.col = col;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
