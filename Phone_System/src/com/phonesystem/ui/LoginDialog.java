package com.phonesystem.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.phonesystem.entity.Admin;
import com.phonesystem.service.AdminService;
import com.phonesystem.unitls.SMSTools;
import com.phonesystem.unitls.YanTool;

public class LoginDialog extends JDialog {
	
	private AdminService adminService = new AdminService();
	
	private  JLabel l=new JLabel("");
	private JLabel phonel=new JLabel("�ֻ��ţ�");
	long random ;  
	
	private JButton  codebb=new JButton("��һ��");
	
	private JLabel yan=new JLabel("��֤�룺");
	
	private JTextField phonet=new JTextField(10);
	private JTextField codet=new JTextField(10); //�ֻ���֤��
	private JLabel codel=new JLabel("�ֻ���֤�룺"); 
	
	private JButton codeb=new JButton("��ȡ");
	
	
	BgPanel pp=new BgPanel();
	
	private JLabel username = new JLabel("�û�:");
	private JLabel userpass = new JLabel("����:");

	private JTextField  code1=new JTextField(10); //��֤��
	
	private JTextField username1 = new JTextField(10);
	private JPasswordField userpass1 = new JPasswordField(10);

	private JButton login = new JButton("��¼");
	private JButton reset = new JButton("���");
	
	public LoginDialog() {
		Container cp = this.getContentPane();
		
		String code=YanTool.getRandom();
		
		 pp.setLayout(new FlowLayout());
		 
		 phonet.setText("");
		 
		 pp.add(phonel);
		 pp.add(phonet);
		 pp.add(codel);
		 pp.add(codet);
		 pp.add(codeb);
		 
		 pp.add(yan); //��֤��
		 l.setText(code);
		 pp.add(l);
		 pp.add(code1);
		 
		 
         pp.add(codebb);
		 
		 pp.add(login);
		 login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
					 long code=Long.parseLong(codet.getText());
					 String s=l.getText();
                 	 String ss=code1.getText();
					 if(code==random&&s.equalsIgnoreCase(ss))
					 {
						
							 String user = username1.getText();
							 String pass = userpass1.getText();
			
							Admin admin = adminService.selectAdmin(user, pass);
							if (admin != null) {
									new MainApp(admin).setVisible(true);
									LoginDialog.this.setVisible(false);
							} else {
									JOptionPane.showMessageDialog(null, "�û������������");
							}
						 
					 }else
				      {
					   JOptionPane.showMessageDialog(null, "�ֻ���֤�����֤�����");
					   new MainApp(null).setVisible(false);
					   
				      }
					
				}
			});
		 
		 pp.add(reset);
		 
		 
		 codeb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					random=(long) (Math.random()*(9999-1000)+1000);
//					System.out.println(random);
					String phone=phonet.getText();
					SMSTools.getCode(phone,random);
				}
			});
		 
		 codebb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				l.setText(YanTool.getRandom());
			}
		});
		
		
		pp.add(username);
		pp.add(username1);
		
		pp.add(userpass);
		pp.add(userpass1);

		pp.add(login);
		

		pp.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userpass1.setText("");
				username1.setText("");
				codet.setText("");
				code1.setText("");
				phonet.setText("");
			}
		});
		
		this.setSize(1200, 100);
		cp.add(pp);
		this.setLocationRelativeTo(null);
		this.setTitle("��¼");
	}

	public static void main(String[] args) {
		new LoginDialog().setVisible(true);
	}
}


