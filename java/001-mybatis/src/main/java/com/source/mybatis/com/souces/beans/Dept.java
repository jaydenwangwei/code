package com.source.mybatis.com.souces.beans;

import java.io.Serializable;

public class Dept implements Serializable {

	private int		id;

	private String	dName;

	private String	loc;



	public Dept() {

	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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
