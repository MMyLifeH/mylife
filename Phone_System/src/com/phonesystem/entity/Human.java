package com.phonesystem.entity;

import java.io.Serializable;

public class Human implements Serializable {
	private int phid;
	private String name;
	private String sex;
	private String email;
	private String number;
	private String familyaddress;
	private String birthday;
	private String pid; // ·Ö×é
	private Phone phone;

	public Human() {

	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public int getPhid() {
		return phid;
	}

	public void setPhid(int phid) {
		this.phid = phid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFamilyaddress() {
		return familyaddress;
	}

	public void setFamilyaddress(String familyaddress) {
		this.familyaddress = familyaddress;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "Human [phid=" + phid + ", name=" + name + ", sex=" + sex + ", email=" + email + ", number=" + number
				+ ", familyaddress=" + familyaddress + ", birthday=" + birthday + ", pid=" + pid + ", phone=" + phone
				+ "]";
	}

}
