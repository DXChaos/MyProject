	
	/**
	 * 模型层。
	 * 此类为ManagerInterface的实现类
	 */
	package allenlink.manager;
	
	import allenlink.model.MoneyBean;
import allenlink.util.MoneyIsNotEnoughException;
//异常类
	import allenlink.util.NegativeMoneyException;

	public class ManagerImpl implements ManagerInterface {
		
		private static ManagerImpl instance;
		private MoneyBean moneyExample=MoneyBean.getInstance(10);		//初始化时的金额是10元。
		
		private ManagerImpl(){
		}
		
		
		/**
		 * 获得一个ManagerImpl类型的对象.
		 * @return ManagerImpl instance 一个ManagerImpl类型的对象
		 */
		
		public static ManagerImpl getInstance(){
			if(instance==null){
				instance=new ManagerImpl();
			}
			return instance;
		}
		
		
		/**
		 * 查询余额，返回用户余额。
		 * @see ManagerInterface
		 * @return double 用户的余额。
		 */
		
		@Override
		public double inquiry() {
			System.out.println(
					"--- --- --- ---欢迎光临--- --- --- ---"+"\n"+
					"		我们将提供如下服务                                   "+"\n"+	
					"		1.查询余额                                          "+"\n"+
					"		2.存款	               "+"\n"+
					"		3.取款                                                 "+"\n"+
					"		4.退出系统	           "+"\n"+
					"--- --- --- ---欢迎光临--- --- --- ---"
				);
			System.out.println("余额为"+moneyExample.getMoney());
			return moneyExample.getMoney();
		}

		/**
		 * 存款。在余额的基础上加上存储的金额。
		 * @param double money 需要存储的金额。
		 * @see ManagerInterface
		 * @throws NegativeMoneyException
		 */
		
		@Override
		public void deposit(double money) throws NegativeMoneyException {
			
				if(money<0){
					throw new NegativeMoneyException("金额为负数");
				}
				moneyExample.setMoney(moneyExample.getMoney()+money);
				this.inquiry();
			
		}

		/**
		 * 取款，在余额的基础上减去取出的金额。
		 * @param double money 需要取出的金额。
		 * @see ManagerInterface
		 * @throws MoneyIsNotEnoughException
		 * 		 	NegativeMoneyException 
		 */
		
		@Override
		public void withdrawy(double money) throws NegativeMoneyException, MoneyIsNotEnoughException  {
				if(money<0){
					throw new NegativeMoneyException("金额为负数");
				}
				if(money>moneyExample.getMoney()){
					throw new MoneyIsNotEnoughException("余额不足");
				}
				moneyExample.setMoney(moneyExample.getMoney()-money);
				this.inquiry();
		}

		@Override
		public void exitSystem() {
			System.exit(1);
		}


		@Override
		public boolean signUp() {
			// TODO Auto-generated method stub
			return false;
		}
	
	}
