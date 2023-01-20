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

import com.phonesystem.entity.Phone;
import com.phonesystem.service.PhoneService;

public class InsertPhoneDialog extends JDialog{
	private PhoneService phoneService=new PhoneService();
	
	private JLabel pidl=new JLabel("���");
	private JTextField pidt=new JTextField(20);
	
	private JLabel pnamel=new JLabel("����");
	private JTextField pnamet=new JTextField(20);
	
	private JButton addb=new JButton("���");
	private JButton restb=new JButton("�ر�");
	
	public InsertPhoneDialog() {
		// TODO Auto-generated constructor stub
		
		Container cp=this.getContentPane();
		cp.setLayout(new FlowLayout());
		
		cp.add(pidl);
		cp.add(pidt);
		
		cp.add(pnamel);
		cp.add(pnamet);
		
		cp.add(addb);
		
		addb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pid=pidt.getText();
				String pname=pnamet.getText();
				
				Phone phone=new Phone();
				
			    phone.setPid(pid);
				phone.setPhonename(pname);
				
				boolean z=phoneService.insertPhone(phone);
				if(z) {
					JOptionPane.showMessageDialog(null,"��ӳɹ�");
				}
				else {
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
			}
		});
		
		cp.add(restb);
		restb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InsertPhoneDialog.this.setVisible(false);
			}
		});
		
		this.setTitle("��ӷ���");
		this.setSize(300,150);
		this.setModal(true);
		this.setLocationRelativeTo(null);
	}
	
	public void clearData() {
		pidt.setText("");
		pnamet.setText("");
	}
	
//	public static void main(String[] args) {
//		new InsertPhoneDialog().setVisible(true);
//	}
}
