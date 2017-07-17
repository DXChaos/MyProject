	
	/** 
	 * 领域模型 Money
	 * 属性只有一 double money
	 * 
	 * 因为只允许有一个MoneyBean对象。
	 * 所以为了安全使用懒汉单例模式。
	 */

	package allenlink.model;
	
	public class MoneyBean {
		
		private double money;
		private static MoneyBean instance;				//单例MoneyBean的对象。
		
		
		private MoneyBean(){
			
		}
		
		
		private MoneyBean(double money){
			this.money=money;
		}
		
		
		public double getMoney() {
			return money;
		}


		public void setMoney(double money) {
			this.money = money;
		}


				
		/**
		 * 返回一个MoneyBean对象。
		 * @param double money 初始化时的金额。
		 * @return	MoneyBean instance 一个MoneyBean对象。
		 */
		
		public  static synchronized MoneyBean getInstance(double money){
			if(instance==null){
				instance=new MoneyBean(money);
			}
			return instance;
		}
		
		
		
	}
