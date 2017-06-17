	
	/**
	 * 业务层。
	 * 此类为ManagerInterface的实现类
	 */
	package allenlink.manager;
	
	import java.io.IOException;

	import allenlink.dao.UserDaoImpl;
	import allenlink.dao.UserDaoInterface;
	
	import allenlink.model.MoneyBean;
	import allenlink.model.User;
	import allenlink.util.MD5Util;
	import allenlink.util.MoneyIsNotEnoughException;
	//异常类
	import allenlink.util.NegativeMoneyException;

	public class ManagerImpl implements ManagerInterface {
		
		private static ManagerImpl instance;
		//模型层对象，在业务层初始化，DAO层通过单例获得，并进行修改。
		private MoneyBean moneyExample=MoneyBean.getInstance(0);		//初始化时的金额是0元。
		private User user=User.getInstance(" ", " ");
		//DAO层对象。
		private UserDaoInterface userDaoInterface=new UserDaoImpl();
		
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
			System.out.println("--- --- --- ---欢迎光临--- --- --- ---");
			System.out.println("		我们将提供如下服务                                   		");
			System.out.println("			1.注册                                       			");
			System.out.println("			2.登录	               		");
			System.out.println("			3.查询                                                 		");
			System.out.println("			4.存款         						");
			System.out.println("			5.取款                                                 		");
			System.out.println("			6.退出系统           					");
			System.out.println("--- --- --- ---欢迎光临--- --- --- ---");
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

		@Override
		public void exitSystem() throws IOException {
			userDaoInterface.saveUserInfor(user.getUserName(), user.getPassWord(), moneyExample.getMoney());
			System.exit(1);
		}
		
	}
