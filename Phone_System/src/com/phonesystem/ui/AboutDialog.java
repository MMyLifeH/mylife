package com.phonesystem.ui;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class AboutDialog extends JDialog {
	private JButton close = new JButton("关闭");

	public AboutDialog() {
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(close);

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		     	AboutDialog.this.setVisible(false);
			}
		});

		this.setSize(300, 300);
		this.setModal(true);// 将对话框变成模态。
		this.setLocationRelativeTo(null);

	}

}
