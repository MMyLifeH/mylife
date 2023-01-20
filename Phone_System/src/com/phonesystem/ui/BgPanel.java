package com.phonesystem.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BgPanel extends JPanel{
	@Override
	protected void paintComponent(Graphics g) {
		 Graphics2D g2 = (Graphics2D) g;
		 super.paintComponent (g);
		 
		 Image img = Toolkit.getDefaultToolkit().getImage("logog.png");
		 
		 g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	 
	}

}
