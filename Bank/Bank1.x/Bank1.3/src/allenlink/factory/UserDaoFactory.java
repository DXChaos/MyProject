	
	package allenlink.factory;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Properties;

	import allenlink.dao.UserDaoInterface;

	public class UserDaoFactory {
		
		private static UserDaoInterface userDaoInterface;
		private static UserDaoFactory userDaoFactory;
		
		private UserDaoFactory(){	

			File file=new File("D:\\Code\\java\\MyProject"
					+ "\\Bank\\Bank1.x\\Bank1.3\\DataBase\\"
					+"DaoClassName.properties");
			Properties pro = new Properties();
			FileInputStream oFile;
			try {
				oFile = new FileInputStream(file);
				pro.load(oFile);			//将从文件中加载到properties对象中。
				oFile.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			Class clazz;
			try {
				clazz = Class.forName(pro.getProperty("className"));
				userDaoInterface=(UserDaoInterface)clazz.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
		public static synchronized UserDaoFactory getInstance(){
			if(userDaoFactory==null){
				userDaoFactory=new UserDaoFactory();
			}
			return userDaoFactory;
		}
		
		public UserDaoInterface getUserDaoInterface(){
			return userDaoInterface;
		}
	}
