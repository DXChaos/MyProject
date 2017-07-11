	
	package allenlink.dao;
	
	import java.io.IOException;
	
	public interface UserDaoInterface {
		
		/**
		 * 查找用户名,并确认用户名是否在数据库中存在。
		 * 存在返回true,不存在，返回false.
		 * @param String userName	注册用户名
		 * @return	boolean	result	查找结果	
		 */
		
		public boolean confirmUserName(String userName);
		
		
		/**
		 * 根据用户名查找对应的密码，
		 * 
		 * 
		 * @param	String userName	用户输入的用户名。
		 * @return	String 返回用户名。
		 * 
		 */
		
		public String confirmPassWord(String userName)throws IOException;
		
		
		/**
		 * 保存用户信息。
		 * 保存成功则返回true,失败则返回false.
		 * @param String userName	用户名
		 * 			String passWord	用户密码
		 * @return	boolean result 保存信息的结果。
		 */
		
		public boolean saveUserInfor(String userName,String passWord,double money) throws IOException;
		
		
		/**
		 * 用户登陆成功后。
		 * 读取用户的所有信息。
		 * @param String userName		用户名
		 * 			String passWord		密码
		 */
		
		public void readUserAllInfo(String userName,String passWord) throws IOException;
	
		
		/**
		 * 根据用户名将相应的金额存入用户数据库中。
		 */
		
		public void moneyTransfer(String name,double money) throws IOException;
	}
