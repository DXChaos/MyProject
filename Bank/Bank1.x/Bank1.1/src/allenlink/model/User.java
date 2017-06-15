	
	/**
	 * 模型层，User类用来保存用户信息的。
	 * 
	 * 属性为String userName 用户名
	 * 	   String passWord 密码
	 * 
	 * 因为只允许有一个User对象。
	 * 所以为了安全使用懒汉单例模式。
	 */

	package allenlink.model;
	
	public class User {
		
		private String userName;
		private String passWord;
		private static User instance;
		
		
		public User(){
		}
		
		
		public User(String userName,String passWord){
			this.userName=userName;
			this.passWord=passWord;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getPassWord() {
			return passWord;
		}


		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
		
		
		/**
		 * 懒汉单例模式，返回一个User对象。
		 * @param String userName	用户名
		 * 		  String passWord	密码
		 * @return User instance 一个User对象。
		 */
		
		public User getInstance(String userName,String passWord){
			if(instance==null){
				instance=new User(userName,passWord);
			}
			return instance;
		}
		

	}
