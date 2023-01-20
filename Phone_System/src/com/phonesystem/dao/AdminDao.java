package com.phonesystem.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.Query;
import com.phonesystem.entity.Admin;

public class AdminDao {
	private QueryRunner runner = new QueryRunner(new ComboPooledDataSource());

	// 插入admin数据
	public boolean insertAdmin(String name, String pass) { // 增加数据
		String sql = "insert into admin (adminname,adminpass) values(?,?)";
		try {
			// int a=runner.update(sql, name,pass); //如果插入数据成功，就返回一个大于0的数，即插入一条返回1，以此类推
			// if(a>0) {
			// return true;
			// }
		   return runner.update(sql, name, pass) > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// 删除数据
	public boolean deleteAdminBy_aid(int aid) {
		String sql = "delete from admin where aid=?";
		try {
			return runner.update(sql, aid) > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 修改数据
	public boolean updateAdminBy_name(String adminname, int aid) {
		String sql = "update admin set adminname=? where aid=?";
		try {
			return runner.update(sql, adminname, aid) > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateAdminBy_pass(String adminpass, int aid) {
		String sql = "update admin set adminpass=? where aid=?";
		try {
			return runner.update(sql, adminpass, aid) > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateAdminBy_role(int role, int aid) {
		String sql = "update admin set role=? where aid=?";
		try {
			return runner.update(sql, role, aid) > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// 查找所有数据
	public List<Admin> selectAdmin() {
		String sql = "select * from admin";
		try {
			return runner.query(sql, new BeanListHandler<Admin>(Admin.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 查找某个详细数据
	public Admin selectAdminx(String adminname, String adminpass) {
		String sql = "select * from admin where adminname=? and adminpass=?";
		try {
			return runner.query(sql, new BeanHandler<Admin>(Admin.class), adminname, adminpass);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	// 计算总数
	public int counts() {
		String sql = "select count(*) from admin";
		try {
			Long l = (Long) runner.query(sql, new ScalarHandler());
			return l.intValue();
			// System.out.println(object.getClass().getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	

    public  boolean updateAdminPass(String adminpass,String adminname)
    {
    	String sql="update admin set adminpass=? where adminname=?";
    	try {
			return runner.update(sql,adminpass,adminname)>0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

}
