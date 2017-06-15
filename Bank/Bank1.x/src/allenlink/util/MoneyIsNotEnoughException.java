	
	/**
	 * 自定义异常类,继承Exception.
	 * 用于提醒用户余额不足。
	 */

	package allenlink.util;
	
	public class MoneyIsNotEnoughException extends Exception {
		
		public MoneyIsNotEnoughException(String msg) 
	    { 
	        super(msg); 
	    } 
		
	}
