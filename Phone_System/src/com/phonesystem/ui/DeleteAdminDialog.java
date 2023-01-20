package com.phonesystem.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.phonesystem.entity.Admin;
import com.phonesystem.service.AdminService;
import com.phonesystem.unitls.MD5;

public class DeleteAdminDialog extends JDialog{
	private AdminService adminService=new AdminService();
	private Admin admin;
	private JLabel l=new JLabel("请输入需要删除的管理员编号：");
	private JTextField p=new JTextField(20);
	private JButton b=new JButton("删除");
	public DeleteAdminDialog() {
		// TODO Auto-generated constructor stub
		this.admin=admin;
		Container cp=this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(l);
		cp.add(p);
		cp.add(b);
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  String aid=p.getText();
				  int id=Integer.parseInt(aid);
				  
				  boolean z=adminService.deleteAdminByaid(id);
				  if(z)
				  {
					  JOptionPane.showMessageDialog(null, "删除成功");
				  }else {
					  JOptionPane.showMessageDialog(null, "删除失败");
					    
				  }
			}
		});
		
		this.setTitle("管理员管理");
		this.setModal(true);
		this.setSize(540,90);
		this.setLocationRelativeTo(null);
	}
	
}
