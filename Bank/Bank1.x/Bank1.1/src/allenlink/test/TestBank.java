	
	package allenlink.test;
	
	import java.io.IOException;
	import java.util.Scanner;
	
	//业务层
	import allenlink.manager.ManagerInterface;
	import allenlink.manager.ManagerImpl;
	
	//异常类
	import allenlink.util.MoneyIsNotEnoughException;
	import allenlink.util.NegativeMoneyException;
	
	public class TestBank {
	
		public static void main(String[] args){
			
			//进入主界面
			System.out.println("--- --- --- ---欢迎光临--- --- --- ---");
			System.out.println("		我们将提供如下服务                                   		");
			System.out.println("			1.注册                                       			");
			System.out.println("			2.登录	               		");
			System.out.println("			3.查询                                                 		");
			System.out.println("			4.存款         						");
			System.out.println("			5.取款                                                 		");
			System.out.println("			6.退出系统           					");
			System.out.println("--- --- --- ---欢迎光临--- --- --- ---");
			System.out.println("请选择您要进行的操作");
			ManagerInterface manager=ManagerImpl.getInstance();	//获取manager对象。
			Scanner scan=new Scanner(System.in);			//使用系统键盘输入作为输入流初始化Scanner.
			int operatorId=0;								//操作指令.
			operatorId=getOperatorId(scan);					//获取int类型的数据。

			while(true){
				if(operatorId==1){
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
				}else if(operatorId==2){
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
					}else{
						System.out.println("用户名或者 密码错误，请重新登陆");
					}
					
				}else if(operatorId==3){
					System.out.println("进行查询");
					manager.inquiry();
				}else if(operatorId==4){
					System.out.println("进行存储，请输入存款金额");
					
					double tempMoney=getOperatorId(scan);
					try {
						manager.deposit(tempMoney);
					} catch (NegativeMoneyException e) {
						System.out.println("存款金额不可以为负数，请重新输入正确的金额");
					}catch(IOException e){
						System.out.println("数据库无法访问");
					}
				}else if(operatorId==5){
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
				}else if(operatorId==6){
					System.out.println("退出系统");
					
					try {
						manager.exitSystem();
					} catch (IOException e) {
						e.printStackTrace();
					}
					scan.close();
				}else{
					System.out.println("请输入正确的字符");
				}
				System.out.println("请选择您要进行的操作");
				operatorId=getOperatorId(scan);
			}
		}

		
		/**
		 * 强制执行要求用户输入固定的指令和数字。
		 * @param scan
		 * @return
		 */
		public static Integer getOperatorId(Scanner scan){
			Integer i=null;
			
			/**
			 * 当某一扫描器抛出 InputMismatchException 时，该扫描器不会传递导致该异常的标记，因此可以通过其他某种方法来获取或跳过它。
				这是java API文档中的原话。意思是说，如果nextInt方法没有成功解析出一个整数，那么你的输入不会被忽略。因为它或许能被其它格式解析。
				那么你的程序中输入了a(一个字符)后，出现异常，开始下一次循环，但这时数据缓冲区内你上次输入的a还在，并没有清除，这时继续解析a，还是错误，于是这个过程就一直重复下去了。
				现在你要改的是，出现错误把这次的输入清除，只要在catch中，加一句：scan.next()就搞定了！
			 */
			
//			while(true){
//				try{
//					i=scan.nextInt();
//					if(i instanceof Integer){
//						break;
//					}
//				}catch(Exception e){
//					System.out.println("请输入正确的指令");
//					scan.next();		//是以一个空格为读取分隔符，(以一个空格开始，以一个空格结束)。(每次读取两个空格之间的数据)。		
//				}
//			}
//		return i;
			
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
