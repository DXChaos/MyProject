	
	/**
	 * 业务层。
	 * 此类为ManagerInterface的实现类
	 */

	package allenlink.manager;
	
	//Dao层。
	import allenlink.dao.UserDaoInterface;
	
	//Factory层
	import allenlink.factory.UserDaoFactory;
	
	//模型层和工具包。
	import allenlink.model.MoneyBean;
	import allenlink.model.User;
	import allenlink.util.MD5Util;
	import allenlink.util.MoneyIsNotEnoughException;
	
	//异常类
	import allenlink.util.NegativeMoneyException;
	import java.io.IOException;

	public class ManagerImpl implements ManagerInterface {
		
		private static ManagerImpl instance;
		
		//模型层对象，在业务层初始化，DAO层通过单例获得，并进行修改。
		private MoneyBean moneyExample=MoneyBean.getInstance(0);		//初始化时的金额是0元。
		private User user=User.getInstance(" ", " ");
		
		//DAO层对象。
		private UserDaoInterface userDaoInterface=UserDaoFactory.getInstance().getUserDaoInterface();	//获取Dao层具体实现类对象通过Factory工厂获得。
		
		private ManagerImpl(){
		}
		
		/**
		 * 	判断注册用户名是否已经存在。
		 * 	通过注册的用户名判断当前用户名是否和已有的properts文件的文件名相同，来判断注册用户名是否已经存在，
		 * 
		 * @return boolean result 是否存注册用户名，存在，返回true.不存在,返回false.
		 * @throws IOException	saveUserInfor抛出的方法。	
		 */
		
		@Override
		public boolean signUp(String userName,String passWord) throws IOException {
			if(userDaoInterface.confirmUserName(userName)){				//当前用户名存在，返回true.
				return true;
			}else{														//当前用户名不存在，返回false,并保存用户信息。
				String tempPass=MD5Util.encode2hex(passWord);
				userDaoInterface.saveUserInfor(userName, tempPass,10);		//保留信息到数据库中。
				return false;
			}
		}
		
		
		/**
		 *	判断登录用户信息是否存在于数据库。
		 *	存在则，返回true,不存在，返回false.
		 *
		 * @return	boolean	result	存在则，返回true,不存在，返回false.
		 * @throws IOException 
		 */
		
		@Override
		public boolean signIn(String userName,String passWord) throws IOException {
			if(userDaoInterface.confirmUserName(userName)){				//当前用户名存在,继续验证密码。
				String exitPassWord=userDaoInterface.confirmPassWord(userName);	//根据用户名查找密码。
				String inputPassWord=MD5Util.encode2hex(passWord);				//将用户输入的密码进行加密和数据库中保存的加密密码进行比较。
				if(inputPassWord.equals(exitPassWord)){							//验证完用户名正确后，将用户名对用的文件信息加载到模型层。根据用户名返回对用文件下的密码信息，不需要保存在模型层。
					
					//将数据库中的信息加载到model层。
					userDaoInterface.readUserAllInfo(userName, inputPassWord);
					
					return true;											//密码正确，用户存在，返回true.
				}else{													//密码不正确，用户信息不存在
					return false;
				}
			}else{														//当前用户名不存在，返回false。
				return false;
			}
		}
	
		
		/**
		 * 获得一个ManagerImpl类型的对象.
		 * @return ManagerImpl instance 一个ManagerImpl类型的对象
		 */
		
		public static synchronized ManagerImpl getInstance(){
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
			//进入操作界面。
			System.out.println("***** ***** ***** 欢迎光临 ***** ***** *****");
			System.out.println("*             我们将提供如下服务                                         *");
			System.out.println("*                1.查询                                                      *");
			System.out.println("*                2.存款                                                      *");
			System.out.println("*                3.取款                                                      *");
			System.out.println("*                4.转账                                                      *");
			System.out.println("*                5.退出系统                                              *");
			System.out.println("***** ***** ***** 欢迎光临 ***** ***** *****");
			System.out.println("请选择您要进行的操作");
			System.out.println("余额为"+moneyExample.getMoney());
			//System.out.println("请选择您要进行的操作");
			return moneyExample.getMoney();
		}

		/**
		 * 存款。在余额的基础上加上存储的金额。
		 * @param double money 需要存储的金额。
		 * @see ManagerInterface
		 * @throws NegativeMoneyException
		 * @throws IOException 
		 */
		
		@Override
		public void deposit(double money) throws NegativeMoneyException, IOException {
			
				if(money<0){
					throw new NegativeMoneyException("金额为负数");
				}
				moneyExample.setMoney(moneyExample.getMoney()+money);
				userDaoInterface.saveUserInfor(user.getUserName(), user.getPassWord(), moneyExample.getMoney());
				this.inquiry();
			
		}

		/**
		 * 取款，在余额的基础上减去取出的金额。
		 * @param double money 需要取出的金额。
		 * @see ManagerInterface
		 * @throws MoneyIsNotEnoughException
		 * 		 	NegativeMoneyException 
		 * @throws IOException 
		 */
		
		@Override
		public void withdrawy(double money) throws NegativeMoneyException, MoneyIsNotEnoughException, IOException  {
				if(money<0){
					throw new NegativeMoneyException("金额为负数");
				}
				if(money>moneyExample.getMoney()){
					throw new MoneyIsNotEnoughException("余额不足");
				}
				moneyExample.setMoney(moneyExample.getMoney()-money);
				userDaoInterface.saveUserInfor(user.getUserName(), user.getPassWord(), moneyExample.getMoney());
				this.inquiry();
		}

		
		/**
		 * 转账。
		 * 输入转账用户，判断转账用户是否存在;
		 * 输入金额，判断是否金额是否够。
		 * @throws IOException 
		 * @throws MoneyIsNotEnoughException 
		 * @throws NegativeMoneyException 
		 */
		
		@Override
		public void moneyTransfer(String name, double money) 
				throws NegativeMoneyException, MoneyIsNotEnoughException, IOException {
			if(userDaoInterface.confirmUserName(name)&&!
					(name.equals(user.getUserName())) ){				//当前用户名存在，则进行转账金额判断。
				
				System.out.println("转账用户存在");
				if(money<=moneyExample.getMoney()&&money>=0){					//转账金额小于或者用户余额。
					System.out.println("转账成功");
					//将金额转入被转账用户。
					userDaoInterface.moneyTransfer(name, money);
					//将金额从转账用户中扣除,利用取款的方式将转账金额从转账金额中扣除。
					withdrawy(money);
				}else{												//金额不够，无法转账。
					System.out.println("你的余额不足或者金额不可以为负");
				}
				
			}else{													
				System.out.println("转账的用户不存在");
			}
		}
		
		
		/**
		 * 退出系统。
		 */
		
		@Override
		public void exitSystem() throws IOException {
			userDaoInterface.saveUserInfor(user.getUserName(), user.getPassWord(), moneyExample.getMoney());
			System.exit(1);
		}

		
	}
