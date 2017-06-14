//
//21:34:17
//戴新超 2017/6/14 21:34:17
//	package com.cx.bank.test;
//	import java.util.Scanner;
//
//	import com.cx.bank.manager.ManagerImpl;
//	import com.cx.bank.manager.IManagerInterface;
//	
//	public class TestBank {
//		
//		
//		/**
//	     * 打印银行系统使用界面
//	     */
//	    public static void print()
//	    {
//	        System.out.println("*****************银行系统1.0******************");
//	        System.out.println("                   1.存款                    ");
//	        System.out.println("                   2.取款                    ");
//	        System.out.println("                   3.查询                    ");
//	        System.out.println("                   4.退出                    ");
//	        System.out.println("*****************银行系统1.0******************");
//	    }
//		
//	    
//		@SuppressWarnings("resource")
//		public static void main(String[] args)
//		{
//			Scanner  s=null;		//创建一台扫描仪
//		 	int num=0;				//用来接收键盘输入的值
//		 	IManagerInterface imp=new ManagerImpl();		//创建业务层对象
//			
//			
//			
//			while(true)
//			{
//				boolean flag=false;			//标记:扫描器输入数据无误时循环才终止
//				lable:while(!flag)
//				{		
//					print();		//打印银行系统界面
//			        System.out.println("请对应功能选择输入数字1~4：");
//			        s=new Scanner(System.in);
//					String line = s.nextLine(); 	//从扫描仪里面读取键盘上输入的字符
//					try{
//						num= Integer.parseInt(line);	//强转为double类型的数据
//			    		}catch(NumberFormatException e){
//			    			System.out.println("您的输入有误");
//			    			continue lable;		//发生异常后,跳回循环处  重新输入
//						}
//					if(num<=4&&num>=1)
//					{
//						flag=true;		//确认num是在功能键值之内
//					}
//					else {
//					System.out.println("您的输入有误");
//					continue lable;				//num不在功能键值之内就重新输入
//					}
//					
//					if(num==1)			//对应键值1 是业务层的存款方法
//						   System.out.println("存款后你的余款为:" +imp.deposit());
//					
//					else if(num==2)		//对应键值1 是业务层的取款方法
//						   System.out.println("取款后你的余款为:" +imp.withdrawals());
//					
//					else if(num==3)		//对应键值1 是业务层的查看余额方法
//						   System.out.println("你的余款为:"+imp.inquiry());
//					
//					else if(num==4) 	//对应键值4 是业务层的退出方法	
//					       imp.exitSystem();
//					    
//						
//					} 
//					
//				//	s.close();
//				} 
//			
//			}
//	
//	    
//	}
