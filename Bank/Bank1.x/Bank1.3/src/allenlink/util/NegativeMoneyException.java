	
	/**
	 * 自定义异常类,继承Exception.
	 * 用于提醒用户输入了负数金额。
	 */
	package allenlink.util;
	
	public class NegativeMoneyException extends Exception {
	
		public NegativeMoneyException(String msg) { 
	        super(msg); 
	    } 
		
	}
