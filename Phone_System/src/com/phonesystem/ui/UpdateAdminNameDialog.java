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

public class UpdateAdminNameDialog extends JDialog{
	private AdminService adminService=new AdminService();
	private Admin admin;
	private JLabel l=new JLabel("���û�����");
	private JTextField p=new JTextField(20);
	private JButton b=new JButton("�޸�");
	public UpdateAdminNameDialog(Admin admin)
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
				  String name=p.getText();
				  
				  int aid=admin.getAid();
				  boolean z=adminService.updateAdminname(name, aid);
				  if(z)
				  {
					  JOptionPane.showMessageDialog(null, "���³ɹ�");
				  }else {
					  JOptionPane.showMessageDialog(null, "����ʧ��");
					    
				  }
			}
		});
		
		this.setTitle("�û�������");
		this.setModal(true);
		this.setSize(400,100);
		this.setLocationRelativeTo(null);
	}
}
