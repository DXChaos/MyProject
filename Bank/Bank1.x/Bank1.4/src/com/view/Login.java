	
	/**
	 * 显示层，登录界面。
	 */
	package com.view;
	
	//业务层
	import allenlink.manager.ManagerImpl;
	import allenlink.manager.ManagerInterface;
	
	//事件以及监听器。
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseAdapter;
	import java.awt.event.WindowEvent;
	import java.io.IOException;
	import java.awt.event.WindowAdapter;
	
	//容器以及组件。
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JTextField;
	import javax.swing.JButton;
	import javax.swing.JOptionPane;			//弹窗。
import javax.swing.JPasswordField;
	
	
	public class Login {
		
		//用户登录界面结构。框架以及组件。
		private JFrame jFrame=new JFrame("登录界面");						//窗口
		private JLabel jLabel=new JLabel("欢迎来到    中国银行");
		private JLabel jLabel1=new JLabel("用户名");						//用户名标签
		private JLabel jLabel2=new JLabel("密码");						//密码标签
		private JTextField jTextField1=new JTextField("请输入用户名");		//文本框  
		private JPasswordField jTextField2=new JPasswordField("请输入密码");		//文本框
		private JButton	jButton1=new JButton("注册");						//注册按钮
		private JButton jButton2=new JButton("登录");						//登录按钮
		private ManagerInterface manager=ManagerImpl.getInstance();		//业务层对象
		
		//监听器。
		private MyActionListener myActionListener=new MyActionListener();	//定义动作监听器对象。
		private MyWindowListener myWinowListener=new MyWindowListener();	//定义窗体监听对象。
		private MyMouseAdapter myMouseListener=new MyMouseAdapter();		//定义鼠标监听对象。
		
		public Login(){
			
			//窗口的相关配置。
			jFrame.setVisible(true);				//让窗口可显示。
			jFrame.setSize(280,280);
			jFrame.setLocation(300, 300);
			jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			//设置默认关闭操作(点击右上角的关闭按钮，不进行任何操作，该事件有监听器去捕获并处理)。
			
			//设置组件的位置,以及大小。使用了绝对定位。
			jFrame.setLayout(null);					//使用绝对定位前，必须要加载一个空的布局。
			jLabel.setBounds(60, 0, 280, 20);
			jLabel1.setBounds(20, 30, 60, 40);
			jTextField1.setBounds(100, 30, 160, 40);
			jLabel2.setBounds(20, 100, 60, 40);
			jTextField2.setBounds(100, 100, 160, 40);
			jButton1.setBounds(20, 160, 60, 40);
			jButton2.setBounds(180, 160, 60, 40);
			
			//将组件加入到窗体容器中。
			jFrame.add(jLabel);
			jFrame.add(jLabel1);
			jFrame.add(jTextField1);
			jFrame.add(jLabel2);
			jFrame.add(jTextField2);
			jFrame.add(jButton1);
			jFrame.add(jButton2);
		
			//将事件监听加入组件中，以及窗体中。
			jFrame.addWindowListener(myWinowListener);
			jButton1.addActionListener(myActionListener);
			jButton2.addActionListener(myActionListener);
			jTextField1.addMouseListener(myMouseListener);
		}

		
		//按钮事件
		class  MyActionListener implements ActionListener{		

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==jButton1){				//按注册按钮，注册是否成功。
					System.out.println("注册按钮被按");
					boolean flag=true;
					
					try {
						flag=manager.signUp(jTextField1.getText(), jTextField2.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(flag){
						JOptionPane.showMessageDialog(jFrame, "当前用户名已存在，请更换用户名");
					}else{
						JOptionPane.showMessageDialog(jFrame, "注册成功，请登录");
						jTextField1.setText("");
						jTextField2.setText("");
					}
		
				}else {										//按登录按钮，看登录是否成功。
					System.out.println("登录按钮被按");
					boolean flag=false;
					
					try {
						flag=manager.signIn(jTextField1.getText(), jTextField2.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(flag){
						JOptionPane.showMessageDialog(jFrame, "登录成功");
						jFrame.dispose();
						new User();
					}else{
						JOptionPane.showMessageDialog(jFrame, "用户名或者密码错误，请确认用户信息");
					}
					
				}
			}
			
		}
	
		
		//窗体事件，点击关闭窗口的按钮，直接出提示信息然后关闭系统。
		class MyWindowListener extends  WindowAdapter{

			@Override
			public void windowClosing(WindowEvent arg0) {
				
				int i=JOptionPane
						.showConfirmDialog(jFrame, "是否要关闭系统");
				if(i==0){
					System.out.println("系统退出");
					System.exit(1);
				}else{
					System.out.println("操作继续");
					
				}
				
			}
			
		}
		
		//鼠标事件，点击当前文本框时，清除当前文本框中的数据。
		class MyMouseAdapter extends MouseAdapter{

			@Override
			public void mouseClicked(MouseEvent e) {
				jTextField1.setText("");
				jTextField2.setText("");
			}
			
		}
		
		public static void main(String[] args){
			new Login();
		}
	}
