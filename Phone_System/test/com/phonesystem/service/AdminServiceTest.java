package com.phonesystem.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdminServiceTest {

	@Test
	public void insertAdminTest() {
		AdminService adminService=new AdminService();
		boolean a=adminService.insertAdmin("������", "sqweqwe");
		System.out.println(a);
	}

}
