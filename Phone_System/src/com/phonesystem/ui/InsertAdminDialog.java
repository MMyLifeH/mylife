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

public class InsertAdminDialog extends JDialog {

	private AdminService adminService = new AdminService();
	private Admin admin = null;
	private JLabel userl = new JLabel("新用户：");
	private JTextField usert = new JTextField(20);
	private JLabel passl = new JLabel("新密码：");
	private JTextField passt = new JTextField(20);
	private JButton b = new JButton("确认");
	private JButton r=new JButton("取消");

	public InsertAdminDialog() {
		// this.admin=admin;
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(userl);
		cp.add(usert);

		cp.add(passl);
		cp.add(passt);

		cp.add(b);
		cp.add(r);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = usert.getText();
				String pass = passt.getText();
				
				Admin admin=new Admin();
				
				String adminpass = MD5.getMD5(pass);
				admin.setAdminname(name);
				admin.setAdminpass(adminpass);
				boolean z = adminService.insertAdmin(name, adminpass);
				if (z) {
					JOptionPane.showMessageDialog(null, "添加成功");
				} else {
					JOptionPane.showMessageDialog(null, "添加失败");

				}
			}
		});

		r.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InsertAdminDialog.this.setVisible(false);
			}
		});

		this.setTitle("添加管理员");
		this.setModal(true);
		this.setSize(350, 150);
		this.setLocationRelativeTo(null);
	}
	

}
