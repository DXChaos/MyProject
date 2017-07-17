	
	package allenlink.dao;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.Properties;
	
	import allenlink.model.MoneyBean;
	import allenlink.model.User;
	
	public class UserDaoImpl implements UserDaoInterface {
		
		//模型层对象。
		User user=User.getInstance(" ", " ");
		MoneyBean moneyBean=MoneyBean.getInstance(20);
		
		/**
		 * 查找注册用户名或者登陆用户名是否在当数据库中存在。
		 * @param	String userName	注册用户名。	
		 * @return	boolean result	查找结果，存在，则返回true;不存在，则返回false。
		 */
		
		@Override
		public boolean confirmUserName(String userName) {
			
			//使用用户名作为文件名，实例化文件对象，在判断当前路径文件是否存在。
			File file=new File("D:\\Code\\java\\MyProject"
					+ "\\Bank\\Bank1.x\\Bank1.3\\DataBase\\"
					+userName+".properties");
			if(file.exists()){			//当前用户名存在。
				return true;
			}else{						//当前用户名不存在。
				return false;
			}
			
		}
	
		
		/**
		 * 保存用户信息。如果是注册时，则是以用户名为文件名，保存用户名和信息,
		 * 			如果是存款，取款或者退出系统时，则将模型层里的数据保存到相应的文件中。
		 * 
		 * 注册时，将用户输入的信息(用户名和密码)作为参数传给这个方法。
		 * 存款，取款或者退出系统时，通过模型层将用户名和密码作为参数给这个方法。
		 * @param	String userName	用户名		
		 * 			String	password 密码
		 * @return	boolean result	信息保存是否成功,成功返回true,失败返回false。
		 * @throws IOException		createNewFile()方法抛出的异常。 
		 */
		
		@Override
		public boolean saveUserInfor(String userName, String passWord,double money) 
				throws IOException {
			
			//实例文件对象，然后以用户名为文件名创建properties文件。
			File file=new File("D:\\Code\\java\\MyProject"
					+ "\\Bank\\Bank1.x\\Bank1.3\\DataBase\\"
					+userName+".properties");
			Properties pro = new Properties();
			if(!file.exists()){
				file.createNewFile();
//				System.out.println(file);
				FileOutputStream oFile = new FileOutputStream(file);
				
				//保存在模型层。
				user.setUserName(userName);
				user.setPassWord(passWord);
				moneyBean.setMoney(money);
				Double d=moneyBean.getMoney();
				
				//保存到properties对象中。
				pro.setProperty("UserName", user.getUserName());
				pro.setProperty("PassWord", user.getPassWord());
				pro.setProperty("money",d.toString());
				
				//持久化到数据库中。
				pro.store(oFile, "comments");
				oFile.close();
				
				return true;
			}else{
//				System.out.println(file);
				FileOutputStream oFile = new FileOutputStream(file);
				
				//保存到模型层
				user.setUserName(userName);
				user.setPassWord(passWord);
				moneyBean.setMoney(money);
				Double d=moneyBean.getMoney();
				
				//b保存到properties对象中。
				pro.setProperty("UserName", user.getUserName());
				pro.setProperty("PassWord", user.getPassWord());
				pro.setProperty("money",d.toString());
				
				//持久化到数据库中。
				pro.store(oFile, "comments");
				oFile.close();
				return true;
			}
		}



		
		/**
		 * 根据用户输入的用户名提取出相应的密码。
		 * @throws IOException 
		 */
		
		@Override
		public String confirmPassWord(String userName) throws IOException {
			
			File file=new File("D:\\Code\\java\\MyProject"
					+ "\\Bank\\Bank1.x\\Bank1.3\\DataBase\\"
					+userName+".properties");
			Properties pro = new Properties();
			FileInputStream oFile = new FileInputStream(file);
			pro.load(oFile);			//将从文件中加载到properties对象中。
			oFile.close();
		
			return pro.getProperty("PassWord");		//从properties对象中将用户名对应的文件中的密码信息返回。		
		}

		
		/**
		 *	在当前用户登陆成功后，读取当前用户的所有信息。
		 *	将信息保存在模型层对象中。
		 *
		 *	@param String userName
		 *			String passWord
		 * @throws IOException 
		 */
		
		@Override
		public void readUserAllInfo(String userName,String passWord) throws IOException {
			File file=new File("D:\\Code\\java\\MyProject"
					+ "\\Bank\\Bank1.x\\Bank1.3\\DataBase\\"
					+userName+".properties");
			Properties pro = new Properties();
			FileInputStream oFile = new FileInputStream(file);
			pro.load(oFile);			//将从文件中加载到properties对象中。
			oFile.close();
			
			user.setUserName(userName);
			user.setPassWord(passWord);
			double temp=Double.parseDouble(pro.getProperty("money"));
			moneyBean.setMoney(temp);
		}


		/**
		 * 转账，根据用户名将相应的用户名下的金额和转账的金额相加并重新保存。
		 * @throws IOException 
		 */
		
		@Override
		public void moneyTransfer(String name, double money) throws IOException {
			File file=new File("D:\\Code\\java\\MyProject"
					+ "\\Bank\\Bank1.x\\Bank1.3\\DataBase\\"
					+name+".properties");
			Properties pro = new Properties();
			FileInputStream oFile = new FileInputStream(file);
			pro.load(oFile);			//将从文件中加载到properties对象中。
			oFile.close();
			
			double tempMoney=Double.parseDouble(pro.getProperty("money"))+money;
			String tempmoney=String.valueOf(tempMoney);
			pro.setProperty("money", tempmoney);
			
			//保存到数据库。
			FileOutputStream oFilei = new FileOutputStream(file);
			pro.store(oFilei, "comments");
			
		}
	
	}
