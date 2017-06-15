	
	/**	业务逻辑块，view层通过调用该层进行操作。
	 * 
	 *  该层有1：存款
			2：取款
			3：查询余额
			4：退出系统
	 * 	总计4个功能。
	 */

	package allenlink.manager;

	import allenlink.util.MoneyIsNotEnoughException;
	import allenlink.util.NegativeMoneyException;
	
	public interface ManagerInterface {
		
		/**
		 * 调用持久层，使用数据，返回查询结果。
		 * @return double money 当前用户的余额。
		 */
		
		public double inquiry();				//查询
		
		
		/**
		 * 输入一个存款金额，调用持久层将金额加入余额中。
		 * @param double money 存款金额
		 * @exception NegativeMoneyException 存款金额为负数。
		 */
		
		public void deposit(double money)throws NegativeMoneyException;				//存款
		
		
		/**
		 * 输入一个取款金额数，调用持久层将金额数从余额中减除。
		 * @param money
		 * @exception NegativeMoneyException 	取款金额为负数
		 * 			  MoneyIsNotEnoughException	余额不足，即取款金额大于存余额。
		 */
		
		public void withdrawy(double money)throws NegativeMoneyException,MoneyIsNotEnoughException;			//取款
		
		
		/**
		 * 操作结束，退出系统。
		 */
		public void exitSystem();			//退出系统
	}
