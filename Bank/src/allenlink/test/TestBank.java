	
	package allenlink.test;
	
	import java.util.Scanner;
	
	//业务层
	import allenlink.manager.ManagerInterface;
	import allenlink.manager.ManagerImpl;
	
	public class TestBank {
	
		public static void main(String[] args) {
			
			//进入主界面
			System.out.println(
				"--- --- --- ---欢迎光临--- --- --- ---"+"\n"+
				"		我们将提供如下服务                                   "+"\n"+	
				"		1.查询余额                                          "+"\n"+
				"		2.存款	               "+"\n"+
				"		3.取款                                                 "+"\n"+
				"		4.退出系统	           "+"\n"+
				"--- --- --- ---欢迎光临--- --- --- ---"
			);
			System.out.println("请选择您要进行的操作");
			
			
			ManagerInterface manager=ManagerImpl.getInstance();	//获取manager对象。
			Scanner scan=new Scanner(System.in);			//使用系统键盘输入作为输入流初始化Scanner.
			boolean flag=true;
			int operatorId=0;								//操作指令.
			operatorId=getOperatorId(scan);					//获取int类型的数据。

			while(true){

				if(operatorId==1){
					System.out.println("进行查询");
					manager.inquiry();
				}else if(operatorId==2){
					System.out.println("进行存储");
					double tempMoney=getOperatorId(scan);
					manager.deposit(tempMoney);
				}else if(operatorId==3){
					System.out.println("进行取款");
					double tempMoney=getOperatorId(scan);
					manager.withdrawy(tempMoney);
				}else if(operatorId==4){
					System.out.println("退出系统");
					manager.exitSystem();
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
			int flag=10;
			
			/**
			 * 当某一扫描器抛出 InputMismatchException 时，该扫描器不会传递导致该异常的标记，因此可以通过其他某种方法来获取或跳过它。
				这是java API文档中的原话。意思是说，如果nextInt方法没有成功解析出一个整数，那么你的输入不会被忽略。因为它或许能被其它格式解析。
				那么你的程序中输入了a(一个字符)后，出现异常，开始下一次循环，但这时数据缓冲区内你上次输入的a还在，并没有清除，这时继续解析a，还是错误，于是这个过程就一直重复下去了。
				现在你要改的是，出现错误把这次的输入清除，只要在catch中，加一句：scan.next()就搞定了！
			 */
				while(flag!=0){
					try{
						i=scan.nextInt();
						if(i instanceof Integer){
							flag=0;
						}
					}catch(Exception e){
						System.out.println("请输入正确的指令");
						scan.next();
					}
				}
		
				
			return i;
		}
	}
