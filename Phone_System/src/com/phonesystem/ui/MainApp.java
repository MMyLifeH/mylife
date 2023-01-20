package com.phonesystem.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import org.junit.experimental.theories.Theories;

import com.phonesystem.entity.Admin;
import com.phonesystem.entity.Human;
import com.phonesystem.entity.Phone;
import com.phonesystem.service.HumanService;
import com.phonesystem.service.PhoneService;

public class MainApp extends JFrame {

	private InsertPhoneDialog insertPhoneDialog = new InsertPhoneDialog();
	private AboutDialog aboutDialog = new AboutDialog();

	private UpdateAdminPassDialog adminPassDialog = null;
	private UpdateAdminNameDialog updateAdminNameDialog = null;

	private Tm tm = new Tm();

	private JTable table = new JTable(tm); // 设置数据排列显示
	private JScrollPane sPane = new JScrollPane(table); // 设置表格
	

	private JLabel ll = new JLabel("管理员:");
	private Admin admin = null;

	private String title = "通讯录管理系统V1.0";

	private String currentphone = "";

	private HumanService humanService = new HumanService();

	private PhoneService phoneService = new PhoneService();
	String phonename[] = phoneService.selectPhoneName();
	JComboBox<String> box = new JComboBox<String>(phonename);
	private Toolkit tool = Toolkit.getDefaultToolkit();

	// 创建JMenuBar
	private JMenuBar bar = new JMenuBar();
	// 创建菜单
	private JMenu xtsz_menu = new JMenu("系统设置[S]");
	private JMenu sjkcz_menu = new JMenu("数据库操作[Q]");
	private JMenu cxxs_menu = new JMenu("查询联系人[Q]");
	private JMenu rstj_menu = new JMenu("人数统计[S]");
	private JMenu help_menu = new JMenu("帮助[H]");

	// xtse_menu_menu菜单条目
	private JMenuItem xtsz_menu_addphone = new JMenuItem("添加分组");
	private JMenuItem xtsz_menu_addadmin = new JMenuItem("添加管理员");
	private JMenuItem xtsz_menu_deladmin = new JMenuItem("删除管理员");
	private JMenuItem xtsz_menu_updateadminname = new JMenuItem("修改用户名");

	// cxxs_menu菜单条目
	private JMenuItem cxxs_menu_serchllr = new JMenuItem("按姓名查询联系人");

	// rstj_menu菜单条目
	private JMenuItem rstj_menu_rstjbypname = new JMenuItem("统计分组人数");
	private JMenuItem rstj_menu_rstj = new JMenuItem("统计所有人");

	// sjkcz_menu菜单条目
	private JMenuItem sjkcz_menu_tjxsjl = new JMenuItem("添加联系人");
	private JMenuItem sjkcz_menu_scxsbjl = new JMenuItem("删除联系人");
	private JMenuItem sjkcz_menu_bjxsbjby_name = new JMenuItem("编辑联系人");
	// help_menu菜单条目

	private JMenuItem help_menu_about = new JMenuItem("关于...");

	// 工具栏的按钮
	JButton xgmm = new JButton("修改密码", new ImageIcon("images/1.png"));//
	JButton cxlxr = new JButton("查询联系人", new ImageIcon("images/3.png"));//
	JButton tjrs = new JButton("统计人数", new ImageIcon("images/4.png"));//
	JButton czrz = new JButton("操作日志", new ImageIcon("images/5.png"));//
	JButton tcxt = new JButton("退出系统", new ImageIcon("images/2.png"));//

	// 创建一个工具栏
	private JToolBar tb = new JToolBar();

	public MainApp(Admin admin) {
//	public MainApp() {

		this.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowLostFocus(WindowEvent arg0) {

			}

			@Override
			public void windowGainedFocus(WindowEvent arg0) {

				selectHumans();
				box.removeAllItems(); // 清除所有条目
				String p[] = phoneService.selectPhoneName();
				for (String string : p) {
					box.addItem(string);
				}
				
				setb(tjrs);
				setb(xgmm);
				setb(cxlxr);
				setb(czrz);
				setb(tcxt);
				
				setm(xtsz_menu);
				setm(sjkcz_menu);
				setm(cxxs_menu);
				setm(rstj_menu);
				setm(help_menu);
				
				seti(cxxs_menu_serchllr);
				seti(xtsz_menu_addphone);
				seti(xtsz_menu_addadmin);
				seti(xtsz_menu_deladmin);
				seti(xtsz_menu_updateadminname);
				seti(rstj_menu_rstjbypname);
				seti(rstj_menu_rstj);
				seti(sjkcz_menu_tjxsjl);
				seti(sjkcz_menu_scxsbjl);
				seti(sjkcz_menu_bjxsbjby_name);
				seti(help_menu_about);
			}
		});

		tm.list = humanService.selectHuman();

		tm.fireTableDataChanged(); // 刷新数据

		this.setTitle(title);
		Image image = tool.getImage("logo.png");
		this.setIconImage(image);

		// 菜单
		bar.add(xtsz_menu);
		
		xtsz_menu.add(xtsz_menu_addphone);
		xtsz_menu_addphone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertPhoneDialog.setVisible(true);
				
			}
		});

		xtsz_menu.add(xtsz_menu_addadmin);
		xtsz_menu_addadmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new InsertAdminDialog().setVisible(true);
			}
		});

		xtsz_menu.add(xtsz_menu_deladmin);
		xtsz_menu_deladmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DeleteAdminDialog().setVisible(true);
			}
		});

		xtsz_menu.add(xtsz_menu_updateadminname);
		xtsz_menu_updateadminname.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateAdminNameDialog = new UpdateAdminNameDialog(admin);
				updateAdminNameDialog.setVisible(true);
			}
		});

		cxxs_menu.add(cxxs_menu_serchllr);
		cxxs_menu_serchllr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s = JOptionPane.showInputDialog("请输入需要查找的联系人姓名：");
				// 调用服务层

				if (s != null) {
					List<Human> list = humanService.selectHumanBy_Name(s);
					JOptionPane.showMessageDialog(null, list);
				}
			}
		});

		bar.add(sjkcz_menu);
		bar.add(cxxs_menu);
		bar.add(rstj_menu);
		bar.add(help_menu);

		// sjkcz_menu菜单条目
		sjkcz_menu.add(sjkcz_menu_tjxsjl);
		sjkcz_menu_tjxsjl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new InsertHumanDialog().setVisible(true);
			}
		});

		sjkcz_menu.add(sjkcz_menu_bjxsbjby_name);
		sjkcz_menu_bjxsbjby_name.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(null, "请输入要编辑的联系人姓名:");

				Human hu = humanService.selectHumanByName(name);

				new UpdateHumanDialog(hu).setVisible(true);

			}
		});

		sjkcz_menu.add(sjkcz_menu_scxsbjl);
		sjkcz_menu_scxsbjl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String pid = JOptionPane.showInputDialog(null, "请输入要删除的联系人的编号：");

				if (pid != null) {
					int phid = Integer.parseInt(pid);

					boolean z = humanService.deleteHumanBy_phId(phid);
					if (z) {
						JOptionPane.showMessageDialog(null, "删除成功");
					} else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}
		});

		// help_menu菜单条目
		help_menu.add(help_menu_about);
		help_menu_about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aboutDialog.setVisible(true);
			}
		});
		
		//rstj_menu菜单条目
		rstj_menu.add(rstj_menu_rstjbypname);
		rstj_menu_rstjbypname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String pname = (String) box.getSelectedItem();
				int counts = humanService.counts(pname);
				JOptionPane.showMessageDialog(null, pname + "的总人数是："+counts);
				
			}
		});
		
		rstj_menu.add(rstj_menu_rstj);
		rstj_menu_rstj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int countss = humanService.countss();//
				JOptionPane.showMessageDialog(null, "所有分组的总人数是：" + countss);
			}
		});

		// 工具栏tb加入相应的按钮
		tb.add(box);

		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					currentphone = (String) e.getItem();
					MainApp.this.setTitle(currentphone + "------" + title);

					selectHumans();
				}
			}
		});
		tb.add(ll);

		tb.add(xgmm);

		xgmm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setbb(xgmm);
				adminPassDialog = new UpdateAdminPassDialog(admin);
				adminPassDialog.setVisible(true);

			}
		});

		ll.setText("管理员" + admin.getAdminname());

		tb.add(cxlxr);
		cxlxr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setbb(cxlxr);
				setbb(cxlxr);
				String s = JOptionPane.showInputDialog("请输入需要查找的联系人姓名：");
				// 调用服务层

				if (s != null) {
					List<Human> list = humanService.selectHumanBy_Name(s);
					JOptionPane.showMessageDialog(null, list);
				}

			}
		});

		tb.add(tjrs);

		tjrs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setbb(tjrs);
				String pname = (String) box.getSelectedItem();
				int counts = humanService.counts(pname);
				JOptionPane.showMessageDialog(null, "当前分组的总人数是："+counts);
			}
		});

		tb.add(czrz);
		czrz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setbb(czrz);
			}
		});
		tb.add(tcxt);

		tcxt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setbb(tcxt);
				int z = JOptionPane.showConfirmDialog(null, "确实退出系统吗？", "信息", JOptionPane.YES_NO_OPTION);
				if (z == 0) {
					System.exit(0);
				}
			}
		});
		Container cp = this.getContentPane();

		// 加入菜单条
		this.setJMenuBar(bar);

		// 将工具栏加入到内容面板的北区。
		cp.add(tb, BorderLayout.NORTH);
		cp.add(sPane);// 讲表格Jtabal加入到中区
		
		
		this.admin = admin;
		Dimension dimension = tool.getScreenSize(); // 得到屏幕分辨率
		this.setSize(dimension);
	}

	public void selectHumans() {
		String phonname = (String) box.getSelectedItem();

		Phone p = phoneService.selectPhoneBy_Name(phonname);

		String pid = p.getPid();

		tm.list = humanService.selectHumanBy_pid(pid);
		tm.fireTableDataChanged();
	}
	
	public void setb(JButton jButton) {
		jButton.setForeground((Color.black));
		jButton.setFont(new Font("方正舒体",Font.PLAIN,18));
		jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void setm(JMenu jMenu) {
		jMenu.setForeground((Color.black));
		jMenu.setFont(new Font("方正舒体",Font.PLAIN,18));
		jMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void seti(JMenuItem jMenuItem) {
		jMenuItem.setForeground((Color.black));
		jMenuItem.setFont(new Font("方正舒体",Font.PLAIN,18));
		jMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void setbb(JButton jButton) {
		jButton.setForeground(new Color(90,202,30));
		jButton.setFont(new Font("楷体",Font.PLAIN,18));
		jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
//	 public static void main(String[] args) {
//	     new MainApp(null).setVisible(true);
//	 }

}
