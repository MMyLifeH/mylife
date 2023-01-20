package com.phonesystem.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.phonesystem.entity.Admin;
import com.phonesystem.entity.Human;

public class HumanDao {
	private QueryRunner runner = new QueryRunner(new ComboPooledDataSource());

	public boolean insertHuman(Human human) {
		String sql = "insert into human(name,sex,email,number,familyaddress,birthday,pid) values(?,?,?,?,?,?,?)";
		try {
			return runner.update(sql, human.getName(), human.getSex(), human.getEmail(), human.getNumber(),
					human.getFamilyaddress(), human.getBirthday(), human.getPid()) > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Human> selectHuman() {
		String sql = "select * from human";
		try {
			return runner.query(sql, new BeanListHandler<Human>(Human.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<Human> selectHumanBy_pid(String pid) {
		String sql = "select * from human where pid=?";
		try {
			return runner.query(sql, new BeanListHandler<Human>(Human.class), pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public List<Human> selectHumanBy__Name(String name) {
		String sql = "select * from human where name=?";
		try {
			return runner.query(sql, new BeanListHandler<Human>(Human.class), name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Human selectHumanBy_Phid(int phid) {
		String sql = "select * from human where phid=?";
		try {
			runner.query(sql, new BeanHandler<Human>(Human.class), phid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Human selectHumanByName(String name) {
		String sql = "select * from human where name=?";
		try {
			return runner.query(sql, new BeanHandler<Human>(Human.class), name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateHuman(Human human) {
		String sql = "update human set name=?,sex=?,email=?,number=?,familyaddress=?,birthday=?,pid=? where phid=?";
		try {
			return runner.update(sql, human.getName(), human.getSex(), human.getEmail(), human.getNumber(),human.getFamilyaddress(), human.getBirthday(), human.getPid(), human.getPhid())>0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch blosck
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteHumanBy_phId(int phid)
	   {
		   String sql="delete from human where phid=?";
		  try {
			return  runner.update(sql,phid)>0?true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return false;
	   }
	
	public int counts(String pid){
		   String sql="SELECT  COUNT(*) FROM human WHERE pid=?";
		   try {
			Long i=(Long)runner.query(sql, new ScalarHandler(),pid);
			   return i.intValue();
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		   return 0;
	}
	
	public int countss() {
		String sql = "SELECT  COUNT(*) FROM human";
		try {
			Long i = (Long) runner.query(sql, new ScalarHandler());
			return i.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	
}
