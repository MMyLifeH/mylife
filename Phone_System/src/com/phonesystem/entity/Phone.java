package com.phonesystem.entity;

import java.io.Serializable;

public class Phone implements Serializable {

	private String pid;
	private String phonename;

	public Phone() {

	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPhonename() {
		return phonename;
	}

	public void setPhonename(String phonename) {
		this.phonename = phonename;
	}

	@Override
	public String toString() {
		return "Phone [pid=" + pid + ", phonename=" + phonename + "]";
	}

}
