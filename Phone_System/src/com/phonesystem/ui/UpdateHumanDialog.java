package com.phonesystem.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.phonesystem.entity.Human;
import com.phonesystem.entity.Phone;
import com.phonesystem.service.HumanService;
import com.phonesystem.service.PhoneService;

public class UpdateHumanDialog extends JDialog {
	private Human human;
	
	private PhoneService phoneService = new PhoneService();
	
	private HumanService humanService = new HumanService();
	
	private JLabel namel = new JLabel("��ϵ��������");
	private JTextField namet = new JTextField(30);

	private JLabel emaill = new JLabel("��ϵ�����䣺");
	private JTextField emailt = new JTextField(30);

	private JLabel numberl = new JLabel("��ϵ�˺��룺");
	private JTextField numbert = new JTextField(30);

	private JLabel addressl = new JLabel("��ϵ�˵�ַ��");
	private JTextField addresst = new JTextField(30);

	private JLabel birthdayl = new JLabel("��ϵ�����գ�");
	private JTextField birthdayt = new JTextField(30);

	private String PhoneName[] = phoneService.selectPhoneName();
	private JComboBox<String> box = new JComboBox<>(PhoneName);

	private JLabel sexl = new JLabel("ѧ���Ա�");
	private JRadioButton sexa = new JRadioButton("��");
	private JRadioButton sexb = new JRadioButton("Ů", true);
	ButtonGroup sex = new ButtonGroup();

	private JButton update = new JButton("����");

	public UpdateHumanDialog(Human human) {
		// TODO Auto-generated constructor stub
		
		this.human=human;
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());

		cp.add(namel);
		cp.add(namet);
		namet.setText(human.getName());
		
		sex.add(sexa);
		sex.add(sexb);
		String sex=human.getSex();
		if(sex.equals("��")) {
			sexa.setSelected(true);
			sexb.setSelected(false);
		}
		if(sex.equals("Ů")) {
			sexa.setSelected(false);
			sexb.setSelected(true);
		}
		
		
		cp.add(emaill);
		cp.add(emailt);
		emailt.setText(human.getEmail());
		
		cp.add(numberl);
		cp.add(numbert);
		numbert.setText(human.getNumber());

		cp.add(addressl);
		cp.add(addresst);
		addresst.setText(human.getFamilyaddress());

		cp.add(birthdayl);
		cp.add(birthdayt);
		birthdayt.setText(human.getBirthday());

		
		
//		System.out.println("human:"+human);
		String pid=human.getPid();
		String pname=phoneService.selectPhoneBy_pid(pid).getPhonename();
		box.setSelectedItem(pname);
		
		cp.add(box);
		
        cp.add(sexl);
		
		cp.add(sexa);
		cp.add(sexb);
		cp.add(update);
		
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String name=namet.getText();
				String sex = "";
				
				if (sexa.isSelected()) {
					sex = sexa.getText();
				}
				if (sexb.isSelected()) {
					sex = sexb.getText();
				}
				
				
				String email=emailt.getText();
				String number=numbert.getText();
				String address=addresst.getText();
				String birthday=birthdayt.getText();
				
				String phonename = (String) box.getSelectedItem();
				// JOptionPane.showMessageDialog(null, phonename);

				Human human1 = new Human();
				human1.setPhid(human.getPhid());
//				System.out.println(human.getPhid());
				human1.setName(name);
				human1.setSex(sex);
				human1.setEmail(email);
				human1.setNumber(number);
				human1.setFamilyaddress(address);
				human1.setBirthday(birthday);
                
				Phone phone = phoneService.selectPhoneBy_Name(phonename);
				human1.setPid(phone.getPid());
				
//				JOptionPane.showMessageDialog(null, human1);
				
				boolean z = humanService.updateHuman(human1);
//				System.out.println(z);
				if (z) {
					JOptionPane.showMessageDialog(null, "���³ɹ�");
					namet.setText("");
					emailt.setText("");
					numbert.setText("");
					addresst.setText("");
					birthdayt.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "����ʧ��");
				}
			}
		});

		this.setModal(true);
		this.setSize(480, 240);
		this.setTitle("������ϵ��");
		this.setLocationRelativeTo(null);

		// TODO Auto-generated constructor stub
		
	}
	
//	public static void main(String[] args) {
//		new UpdateHumanBy_nameDialog().setVisible(true);
//	}
}
