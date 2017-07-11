	
	/**
	 * 显示层，用户操作界面。 
	 */
	package com.view;

	//业务层
	import allenlink.manager.ManagerImpl;
	import allenlink.manager.ManagerInterface;
	
	//工具异常类
	import allenlink.util.MoneyIsNotEnoughException;
	import allenlink.util.NegativeMoneyException;
	
	//框架以及组件。
	import javax.swing.JFrame;
	import javax.swing.JButton;
	import javax.swing.JOptionPane;

	//事件以及监听器。
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.WindowEvent;
	import java.io.IOException;
	import java.awt.event.WindowAdapter;

	public class User {
		
		//用户操作界面结构，定义相关界面以及组件。
		private JFrame jFrame=new JFrame("用户操作界面");
		private JButton jButton1=new JButton("查询");				//查询当前余额
		private JButton jButton2=new JButton("存款");				//存款
		private JButton jButton3=new JButton("取款");				//取款
		private JButton jButton4=new JButton("转账");				//转账
		private JButton jButton5=new JButton("退出");				//退出系统
		private ManagerInterface manager=ManagerImpl.getInstance();		//业务逻辑对象
		
		//监听器
		private MyWindowListener myWinowListener=new MyWindowListener();	//定义窗体监听对象。
		private MyActionListener myActionListener=new MyActionListener();	//定义动作监听器对象。
		
		public User(){
			
			//窗口相关设置
			jFrame.setBounds(280, 280, 240, 400);
			jFrame.setVisible(true);
			jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			//设置默认关闭操作(点击右上角的关闭按钮，不进行任何操作，该事件有监听器去捕获并处理)。

			//设置组件的位置,以及大小。
			jFrame.setLayout(null);
			jButton1.setBounds(80,40,80,40);
			jButton2.setBounds(80,100,80,40);
			jButton3.setBounds(80,160,80,40);
			jButton4.setBounds(80,220,80,40);
			jButton5.setBounds(80,280,80,40);
			
			//将标签加入到窗体容器中。
			jFrame.add(jButton1);
			jFrame.add(jButton2);
			jFrame.add(jButton3);
			jFrame.add(jButton4);
			jFrame.add(jButton5);
			
			//将事件监听加入组件中。
			jFrame.addWindowListener(myWinowListener);
			jButton1.addActionListener(myActionListener);
			jButton2.addActionListener(myActionListener);
			jButton3.addActionListener(myActionListener);
			jButton4.addActionListener(myActionListener);
			jButton5.addActionListener(myActionListener);
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
		
		//按钮事件
		class  MyActionListener implements ActionListener{		

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==jButton1){	    										//查询	
					System.out.println("查询");
					double money=manager.inquiry();
					JOptionPane.showMessageDialog(jFrame, "当前余额为"+money);
				}else if(e.getSource()==jButton2){											//存款						
					System.out.println("存款");
					String tempMoney=JOptionPane.showInputDialog(null,"请输入存款金额，存款金额不可以为负数");
					if(tempMoney.contains("+")){
						JOptionPane.showMessageDialog(jFrame, "非法字符，请重新输入");
						return;
					}
					try {
						double money=Double.parseDouble(tempMoney);
						manager.deposit(money);
					} catch (NegativeMoneyException e1) {
						JOptionPane.showMessageDialog(jFrame, "金额为负，请重新输入");
					} catch (IOException e1) {
						e1.printStackTrace();
					}catch(Exception e1){
						JOptionPane.showMessageDialog(jFrame, "非法字符，请重新输入");
					}

				}else if(e.getSource()==jButton3){											//取款
					System.out.println("取款");
					String tempMoney=JOptionPane.showInputDialog(null,"请输入取款金额，取款金额不可以为负数或者多于您的余额");
					if(tempMoney.contains("+")){
						JOptionPane.showMessageDialog(jFrame, "请不要输入+(加号)，请重新输入");
						return;
					}
					
					try {
						double money=Double.parseDouble(tempMoney);
						manager.withdrawy(money);
					} catch (NegativeMoneyException e1) {
						JOptionPane.showMessageDialog(jFrame, "金额为负数，请重新输入");
					} catch (MoneyIsNotEnoughException e1) {
						JOptionPane.showMessageDialog(jFrame, "您的余额不足，请重新输入");
					} catch (IOException e1) {
						e1.printStackTrace();
					}catch(Exception e1){
						JOptionPane.showMessageDialog(jFrame, "非法字符，请重新输入");
					}
					
				}else if(e.getSource()==jButton4){											//转账
					System.out.println("转账");
					String name=JOptionPane.showInputDialog(null, "请输入转账用户名");
					String tempMoney=JOptionPane.showInputDialog(null, "请输入转账金额");
					if(tempMoney.contains("+")){
						JOptionPane.showMessageDialog(jFrame, "请不要输入+(加号)，请重新输入");
						return;
					}
					
					try {
						double money=Double.parseDouble(tempMoney);
						boolean flag=manager.moneyTransfer(name,money);
						if(flag){
							JOptionPane.showMessageDialog(jFrame, "转账成功");
						}else{
							JOptionPane.showMessageDialog(jFrame, "转账用户不存在");
						}
					} catch (NegativeMoneyException e1) {
						JOptionPane.showMessageDialog(jFrame, "您的金额为负数");
					} catch (MoneyIsNotEnoughException e1) {
						JOptionPane.showMessageDialog(jFrame, "您的金额不足");
					} catch (IOException e1) {
						
					}catch(Exception e1){
						JOptionPane.showMessageDialog(jFrame, "非法数值，请重新输入");
					}
		
				}else{
					System.out.println("退出");												//退出
					int flag=JOptionPane.showConfirmDialog(jFrame, "是否退出系统");
					if(flag==0){
						JOptionPane.showMessageDialog(jFrame, "正在退出系统");
						System.exit(0);
					}else{
						System.out.println("操作继续");
					}
					
				}
			}
			
		}
		
	}
