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

public class UpdateAdminPassDialog extends JDialog{
	private AdminService adminService=new AdminService();
	private Admin admin;
	private JLabel l=new JLabel("新密码：");
	private JTextField p=new JTextField(20);
	private JButton b=new JButton("修改");
	public UpdateAdminPassDialog(Admin admin)
	{
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
				String pass=p.getText();
				  String  adminpass=MD5.getMD5(pass);
				  String adminname=admin.getAdminname();
				  boolean z=adminService.updateAdminPass(adminpass, adminname);
				  if(z)
				  {
					  JOptionPane.showMessageDialog(null, "更新成功");
				  }else {
					  JOptionPane.showMessageDialog(null, "更新失败");
					    
				  }
			}
		});
		
		this.setTitle("密码管理");
		this.setModal(true);
		this.setSize(400,100);
		this.setLocationRelativeTo(null);
		
	}
}
