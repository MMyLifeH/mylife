package com.phonesystem.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.SealedObject;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.phonesystem.entity.Human;
import com.phonesystem.entity.Phone;
import com.phonesystem.service.HumanService;
import com.phonesystem.service.PhoneService;

public class InsertHumanDialog extends JDialog {
	private HumanService humanService = new HumanService();

	private PhoneService phoneService = new PhoneService();

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

	private JButton save = new JButton("����");

	public InsertHumanDialog() {
		// TODO Auto-generated constructor stub

		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());

		cp.add(namel);
		cp.add(namet);

		cp.add(emaill);
		cp.add(emailt);

		cp.add(numberl);
		cp.add(numbert);

		cp.add(addressl);
		cp.add(addresst);

		cp.add(birthdayl);
		cp.add(birthdayt);

		cp.add(box);

		cp.add(sexl);
		sex.add(sexa);
		sex.add(sexb);
		cp.add(sexa);
		cp.add(sexb);

		cp.add(save);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String name = namet.getText();
				String email = emailt.getText();
				String number = numbert.getText();
				String address = addresst.getText();
				String birthday = birthdayt.getText();
				String sex = "";
				if (sexa.isSelected()) {
					sex = sexa.getText();
				}
				if (sexb.isSelected()) {
					sex = sexb.getText();
				}
				String phonename = (String) box.getSelectedItem();
				// JOptionPane.showMessageDialog(null, phonename);

				Human human = new Human();
				human.setName(name);
				human.setSex(sex);
				human.setEmail(email);
				human.setNumber(number);
				human.setFamilyaddress(address);
				human.setBirthday(birthday);

				Phone phone = phoneService.selectPhoneBy_Name(phonename);
				human.setPid(phone.getPid());

				// System.out.println(human);
				boolean z = humanService.insertHuman(human);
				if (z) {
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
					namet.setText("");
					emailt.setText("");
					numbert.setText("");
					addresst.setText("");
					birthdayt.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
			}
		});

		this.setModal(true);
		this.setSize(480, 240);
		this.setTitle("�����ϵ�˶Ի���");
		this.setLocationRelativeTo(null);
	}

}
