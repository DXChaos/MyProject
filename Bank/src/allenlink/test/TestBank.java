	
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

	
		public static Integer getOperatorId(Scanner scan){
			Integer i=null;
			
			
				
					try{
							i=scan.nextInt();
						if(i instanceof Integer){
							
						}
					}catch(Exception e){
						System.out.println("请输入正确的指令");
						
					}
				
		
				
			return i;
		}
	}
