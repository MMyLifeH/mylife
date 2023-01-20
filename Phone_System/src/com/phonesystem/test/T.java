package com.phonesystem.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.ldap.SortControl;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class T {
	public static void main(String[] args) {
		ComboPooledDataSource source = new ComboPooledDataSource();
		try {
			Connection con = source.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
