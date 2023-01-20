package com.phonesystem.service;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.phonesystem.dao.HumanDao;
import com.phonesystem.dao.PhoneDao;
import com.phonesystem.entity.Human;
import com.phonesystem.entity.Phone;

public class HumanService {
	private HumanDao humanDao = new HumanDao();
	private PhoneDao phoneDao = new PhoneDao();

	public boolean insertHuman(Human human) {
		return humanDao.insertHuman(human);
	}

	public List<Human> selectHuman() {
		List<Human> list = humanDao.selectHuman();
		for (Human s : list) {
			String phoneId = s.getPid();
			Phone phone = phoneDao.selectPhoneBy_pid(phoneId);
			s.setPhone(phone);
		}
		return list;
	}

	public List<Human> selectHumanBy_Name(String name) {
		List<Human> list2 = humanDao.selectHumanBy__Name(name);
		for (Human ss : list2) {
			String phoneId=ss.getPid();
			Phone phone = phoneDao.selectPhoneBy_pid(phoneId);
			ss.setPhone(phone);
		}
		return list2;
	}

	public List<Human> selectHumanBy_pid(String pid) {
		List<Human> list3 = humanDao.selectHumanBy_pid(pid);
		for (Human sss : list3) {
			Phone phone = phoneDao.selectPhoneBy_pid(sss.getPid());
			sss.setPhone(phone);
		}
		return list3;

	}

	public Human selectHumanByName(String name) {
		return humanDao.selectHumanByName(name);
	}

	public boolean updateHuman(Human human) {
		return humanDao.updateHuman(human);
	}
	
	public boolean deleteHumanBy_phId(int phid) {
		return humanDao.deleteHumanBy_phId(phid);
	}
	
	public int counts(String phonename) {
		Phone p = phoneDao.selectPhoneName(phonename);
		return humanDao.counts(p.getPid());
	}
	
	public int countss() {
		return humanDao.countss();
	}
	
//	 public static void main(String[] args) {
//		 HumanService humanService=new HumanService();
//		 humanService.counts("ÅóÓÑ");
//	 }

	// public static void main(String[] args) {
	// List list=(List) new HumanService().updateHumanBy_Name("Íõ¼ÒÄþ");
	// System.out.println(list);
	// }

}
