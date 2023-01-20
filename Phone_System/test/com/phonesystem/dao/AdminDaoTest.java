package com.phonesystem.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.phonesystem.entity.Admin;

public class AdminDaoTest {

	AdminDao adminDao = null;

	@Before
	public void first() {
		adminDao = new AdminDao();
	}

	@After
	public void end() {
		adminDao = null;
	}

	@Test
	public void insertAdminTestTest() {
		boolean a = adminDao.insertAdmin("õõ²õ", "123456");
		assertEquals(true, a);
	}

	@Test
	public void deleteAdminBy_aidTest() {
		boolean a = adminDao.deleteAdminBy_aid(12);
		assertEquals(true, a);
	}

	@Test
	public void updateAdminBy_nameTest() {
		boolean a = adminDao.updateAdminBy_name("õõ²õ", 5);
		assertEquals(true, a);
	}

	@Test
	public void updateAdminBy_passTest() {

		boolean a = adminDao.updateAdminBy_pass("888888", 5);
		assertEquals(true, a);
	}

	@Test
	public void updateAdminBy_roleTest() {
		boolean a = adminDao.updateAdminBy_role(1, 6);
		assertEquals(true, a);
	}

	@Test
	public void selectAdminxTest() {
		Admin admin = adminDao.selectAdminx("õõ²õ", "123456");
		System.out.println(admin);
	}

	@Test
	public void selectAdminTest() {
		List<Admin> list = adminDao.selectAdmin();
		for (Admin a : list) {
			System.out.println(a);
		}
		// System.out.println(list);
	}

	@Test
	public void countsTest() {
		int a = adminDao.counts();
		System.out.println(a);
	}

}
