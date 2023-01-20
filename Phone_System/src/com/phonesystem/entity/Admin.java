package com.phonesystem.entity;

import java.io.Serializable;

public class Admin implements Serializable {
	private int aid; // ×ÔÔö
	private String adminname;
	private String adminpass;
	private int role; // Ä¬ÈÏ

	public Admin() {

	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getAdminpass() {
		return adminpass;
	}

	public void setAdminpass(String adminpass) {
		this.adminpass = adminpass;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", adminname=" + adminname + ", adminpass=" + adminpass + ", role=" + role + "]";
	}

}
