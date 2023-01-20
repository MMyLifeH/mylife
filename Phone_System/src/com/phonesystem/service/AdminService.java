package com.phonesystem.service;

import com.phonesystem.dao.AdminDao;
import com.phonesystem.entity.Admin;
import com.phonesystem.unitls.MD5;

public class AdminService {
	private AdminDao adminDao = new AdminDao();

	public boolean insertAdmin(String adminname, String adminpass) {
	    String Anpass = MD5.getMD5(adminpass);
		return adminDao.insertAdmin(adminname, Anpass);
	}

	public Admin selectAdmin(String adminname, String adminpass) {

		return adminDao.selectAdminx(adminname, adminpass);
	}
	
	public boolean updateAdminPass(String adminpass,String adminname) {
		return adminDao.updateAdminPass(adminpass,adminname);
	}
	
	public boolean deleteAdminByaid(int aid) {
		return adminDao.deleteAdminBy_aid(aid);
	}
	
	public boolean updateAdminname(String adminname,int aid) {
		return adminDao.updateAdminBy_name(adminname, aid);
	}
	
	
}
