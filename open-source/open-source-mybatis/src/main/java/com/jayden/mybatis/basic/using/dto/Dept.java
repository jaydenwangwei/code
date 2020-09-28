package com.jayden.mybatis.basic.using.dto;

import java.io.Serializable;

public class Dept implements Serializable {
    private static final long serialVersionUID = 0L;

    private String deptNo;

    private String dName;

    private String loc;

    public Dept() {
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
