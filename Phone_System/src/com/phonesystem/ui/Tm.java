package com.phonesystem.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.phonesystem.entity.Human;

public class Tm extends AbstractTableModel {

	public List<Human> list = null;

	String Th[] = { "编号", "姓名", "性别", "邮箱", "号码", "住址", "生日", "分组" };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return Th.length;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return Th[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Human human = list.get(rowIndex);
		if (columnIndex == 0) {
			return human.getPhid();
		} else if (columnIndex == 1) {
			return human.getName();
		} else if (columnIndex == 2) {
			return human.getSex();
		} else if (columnIndex == 3) {
			return human.getEmail();
		} else if (columnIndex == 4) {
			return human.getNumber();
		} else if (columnIndex == 5) {
			return human.getFamilyaddress();
		} else if (columnIndex == 6) {
			return human.getBirthday();
		} else if (columnIndex == 7) {
			return human.getPhone().getPhonename();
		}
		return null;
	}

}
