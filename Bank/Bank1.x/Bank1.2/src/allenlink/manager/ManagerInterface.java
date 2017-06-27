	
	/**	业务逻辑块，view层通过调用该层进行操作。
	 * 
	 *  该层有1：注册
	 *  	2：登录
	 *  	3：存款
			4：取款
			5：查询余额
			6：退出系统
	 * 	总计4个功能。
	 */

	package allenlink.manager;

	import java.io.IOException;

	import allenlink.util.MoneyIsNotEnoughException;
	import allenlink.util.NegativeMoneyException;
	
	public interface ManagerInterface {
		
		/**
		 * 注册
		 * 调用持久层，将注册信息(用户名和密码)保存在以用户名为文件名的properts文件。
		 * @param String userName	用户的注册名，用户通过控制台输入的。
		 * @return boolean result 注册是否成功，成功返回true,失败返回false.
		 * @throws IOException
		 */
		
		public boolean signUp(String userName,String passWord)throws IOException; 
		
		
		/**
		 * 登录
		 * 调用持久层，根据用户输入的信息 
		 * @return boolean result 登录是否成功，成功返回true,失败返回false.
		 * @throws IOException
		 */
		
		public boolean signIn(String userName,String passWord) throws IOException;
		
		
		/**
		 * 查询
		 * 调用持久层，使用数据，返回查询结果。
		 * @return double money 当前用户的余额。
		 */
		
		public double inquiry();				
		
		
		/**
		 * 存款
		 * 输入一个存款金额，调用持久层将金额加入余额中。
		 * @param double money 存款金额
		 * @exception NegativeMoneyException 存款金额为负数。
		 */
		
		public void deposit(double money)throws NegativeMoneyException,IOException;				//存款
		
		
		/**
		 * 取款
		 * 输入一个取款金额数，调用持久层将金额数从余额中减除。
		 * @param money
		 * @exception NegativeMoneyException 	取款金额为负数
		 * 			  MoneyIsNotEnoughException	余额不足，即取款金额大于存余额。
		 */
		
		public void withdrawy(double money)throws NegativeMoneyException,
			MoneyIsNotEnoughException,IOException;			//取款
		
		
		/**
		 * 转账
		 * @param	String name 	用户名
		 * 			double money 	转账金额
		 */
		public void moneyTransfer(String name,double money);
		
		
		/**
		 * 退出系统
		 * 操作结束，退出系统。
		 * @throws IOException
		 */
		public void exitSystem()throws IOException;			//退出系统
		
	}
