package com.phonesystem.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.phonesystem.entity.Phone;

public class PhoneDao {
	private QueryRunner runner = new QueryRunner(new ComboPooledDataSource());

	public List<Phone> selectPhone() {
		String sql = "select * from phone";

		try {
			return runner.query(sql, new BeanListHandler<Phone>(Phone.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Phone selectPhoneByName(String name) {
		String sql = "select * from phone where phonename=?";
		try {
			return runner.query(sql, new BeanHandler<Phone>(Phone.class), name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Phone selectPhoneBy_pid(String pid) {
		String sql = "select * from phone where pid=?";
		try {
			return runner.query(sql, new BeanHandler<Phone>(Phone.class), pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		     e.printStackTrace();
		}
		return null;
	}
	
	 public boolean insertPhone(Phone phone)
	    {
	    	String sql="insert into phone values(?,?)";
	    	try {
				return runner.update(sql,phone.getPid(),phone.getPhonename())>0?true:false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return false;
	    	
	    }
	 
	 public Phone selectPhoneName(String name) {
	    	String sql="select * from phone where phonename=?";
	    	try {
				return runner.query(sql, new BeanHandler<Phone>(Phone.class),name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return null;
	    	
	    }
	
}
