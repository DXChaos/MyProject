	
	package allenlink.test;
	
	import java.io.IOException;
	import java.util.Scanner;
	
	//业务层
	import allenlink.manager.ManagerInterface;
	import allenlink.manager.ManagerImpl;
	
	//工具类--异常类
	import allenlink.util.MoneyIsNotEnoughException;
	import allenlink.util.NegativeMoneyException;
	
	public class TestBank {
	
		public static void main(String[] args){
			
			printMainSurface();										//输出主界面。
			ManagerInterface manager=ManagerImpl.getInstance();		//获取manager对象。
			Scanner scan=new Scanner(System.in);					//使用系统键盘输入作为输入流初始化Scanner.
			int operatorId=0;										//操作指令.
			operatorId=getOperatorId(scan);							//获取int类型的数据。

			while(true){
				if(operatorId==1){
					signUp(manager, scan);					//注册。
				}else if(operatorId==2){
					signIn(manager, scan);					//登录，只有登录后才可以进行相关操作。
				}else if(operatorId==3){
					systemExit(manager, scan);				//退出系统。
				}else{
					System.out.println("请输入正确的字符");
				}
				System.out.println("请选择您要进行的操作");
				operatorId=getOperatorId(scan);
			}
				
		}

		/**
		 * 输出主界面。
		 */
		
		private static void printMainSurface() {
			//进入主界面,主界面就是登陆界面。
			System.out.println("***** ***** ***** 欢迎光临 ***** ***** *****");
			System.out.println("*             我们将提供如下服务                                         *");
			System.out.println("*                1.注册                                                      *");
			System.out.println("*                2.登录                                                      *");
			System.out.println("*                3.退出系统                                              *");
			System.out.println("***** ***** ***** 欢迎光临 ***** ***** *****");
			System.out.println("请选择您要进行的操作");
		}


		/**
		 * 注册操作。
		 * @param manager
		 * @param scan
		 */
		
		private static void signUp(ManagerInterface manager, Scanner scan) {
			System.out.println("进行注册操作");
			boolean flag=true;
			System.out.println("请输入注册用户名");
			String tempName=scan.next();
			System.out.println("请输入注册密码");
			String tempPass=scan.next();
			
			try {
				flag = manager.signUp(tempName, tempPass);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(!flag){
				System.out.println("注册成功");
			}else{
				System.out.println("当前用户名已存在，请换一个用户名");
			}
		}

				
		/**
		 * 登录操作。
		 * @param manager
		 * @param scan
		 */
		
		private static void signIn(ManagerInterface manager, Scanner scan) {
			System.out.println("进行登录操作");
			System.out.println("请输入用户名");
			String tempName=scan.next();
			System.out.println("请输入密码");
			String tempPass=scan.next();
			boolean flag=false;
			try {
				flag=manager.signIn(tempName,tempPass);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(flag){
				
				System.out.println("欢迎登陆");
				System.out.println("***** ***** ***** 欢迎光临 ***** ***** *****");
				System.out.println("*             我们将提供如下服务                                         *");
				System.out.println("*                1.查询                                                      *");
				System.out.println("*                2.存款                                                      *");
				System.out.println("*                3.取款                                                      *");
				System.out.println("*                4.转账                                                      *");
				System.out.println("*                5.退出系统                                              *");
				System.out.println("***** ***** ***** 欢迎光临 ***** ***** *****");
				System.out.println("请选择您要进行的操作");
				
				int tempNum=getOperatorId(scan);
				
				while(true){
					
					if(tempNum==1){
						inquiry(manager);				//查询
					}else if(tempNum==2){
						deposite(manager, scan);		//存款
					}else if(tempNum==3){
						withdraw(manager, scan);		//取款
					}else if(tempNum==4){
						moneyTransfer(manager, scan);	//转账
					}else if(tempNum==5){
						systemExit(manager,scan);		//退出系统
					}else{
						System.out.println("请输入正确的字符");
					}
					System.out.println("请选择您要进行的操作");
					tempNum=getOperatorId(scan);
				}
			}else{
				System.out.println("用户名或者 密码错误，请重新登陆");
			}
		}

		
		/**
		 * 退出系统。 
		 * @param manager
		 * @param scan
		 */
		
		private static void systemExit(ManagerInterface manager, Scanner scan) {
			System.out.println("退出系统");
			
			try {
				manager.exitSystem();
			} catch (IOException e) {
				e.printStackTrace();
			}
			scan.close();
		}

		
		/**
		 * 查询。 
		 */
		
		private static void inquiry(ManagerInterface manager) {
			System.out.println("进行查询");
			manager.inquiry();
		}
		
		
		/**
		 * 存储。
		 */
		
		private static void deposite(ManagerInterface manager, Scanner scan) {
			System.out.println("进行存储，请输入存款金额");
			
			double tempMoney=getOperatorId(scan);
			try {
				manager.deposit(tempMoney);
			} catch (NegativeMoneyException e) {
				System.out.println("存款金额不可以为负数，请重新输入正确的金额");
			}catch(IOException e){
				System.out.println("数据库无法访问");
			}
		}
		
		
		/**
		 * 取款。
		 */
		
		private static void withdraw(ManagerInterface manager, Scanner scan) {
			System.out.println("进行取款,请输入取款金额");
			double tempMoney=getOperatorId(scan);
			
			try {
				manager.withdrawy(tempMoney);
			} catch (NegativeMoneyException e) {
				System.out.println("取款金额不可以为负数，请重新输入正确的金额");
			} catch (MoneyIsNotEnoughException e) {
				System.out.println("余额不足，请重新输入正确的金额");
			}catch(IOException e){
				System.out.println("数据库无法访问");
			}
		}

		
		/**
		 * 转账。
		 * @param manager
		 * @param scan
		 */
		
		private static void moneyTransfer(ManagerInterface manager, Scanner scan) {
			System.out.println("请输入转账用户");
			String name=scan.next();
			System.out.println("请输入转账金额");
			double money=getOperatorId(scan);
			try {
				manager.moneyTransfer(name, money);
			} catch (NegativeMoneyException e) {
				e.printStackTrace();
			} catch (MoneyIsNotEnoughException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	
		/**
		 * 强制执行要求用户输入固定的指令和数字。
		 * @param scan
		 * @return
		 */
		
		public static Integer getOperatorId(Scanner scan){
			Integer i=null;
			
			while(true){
					try{
						String message=scan.nextLine();	
						/**
						 * 字符串使用replaceAll()方法替换 * ? + / | 等字符的时候会报以下异常
							Dangling meta character '*' near index 0
							这主要是因为这些符号在正则表达示中有相应意义。
							只需将其改为 [*] 或 //* 即可。
							
							Java中的replaceAll替换加号
							原来replaceAll的第一个参数是正则，而+在正则中是特殊字符。需要转义。
							正确的写法是replaceAll("\\+","-");
						 */
						message=message.replaceAll("\\+", " ").replaceAll("-", " ");	//当用户输入"+数字"或"-数字"时，则替换成" ".
						i=Integer.parseInt(message);		//"+1"会被转型为1，"-1"会被转型为-1.
						if(i instanceof Integer){
							break;
						}
					}catch(Exception e){
						System.out.println("请输入正确的指令");
					}
				}
			return i;
		}

		
	}
		
	