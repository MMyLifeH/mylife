package com.phonesystem.service;

import java.util.List;
import java.util.Stack;

import com.phonesystem.dao.PhoneDao;
import com.phonesystem.entity.Phone;

public class PhoneService {

	private PhoneDao phoneDao = new PhoneDao();

	public String[] selectPhoneName() {
		List<Phone> list = phoneDao.selectPhone();
		int size = list.size();
		String ss[] = new String[size];
		int i = 0;
		for (Phone s : list) {
			ss[i] = s.getPhonename();
			i++;
		}
		return ss;
	}

	public Phone selectPhoneBy_Name(String name) {
		return phoneDao.selectPhoneName(name);
	}

	public Phone selectPhoneBy_pid(String pid) {
		return phoneDao.selectPhoneBy_pid(pid);
	}
	
	public boolean insertPhone(Phone phone) {
	     return phoneDao.insertPhone(phone);
	}

	// public static void main(String[] args) {
	// String ss[]=new PhoneService().selectPhoneName();
	// for(String s:ss) {
	// System.out.println(s);
	// }
	// }
}
