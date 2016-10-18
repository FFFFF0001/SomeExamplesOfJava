/*
 * UserDBBean.java
 *
 * Created on 2006年5月17日, 下午5:23
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cn.edu.uestc.pandawireless;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
/**
 *
 * @author 熊猫晓希
 */
public class UserDBBean {
    private Connection conn;
    
    
    /**
	 * 读取数据库属性文件db.properties中的数据库连接信息
	 * 并且加载数据库驱动程序
	 *
	 */
	private void init(){
		//读取属性文件中的信息
//		InputStream is = getClass().getResourceAsStream("db.properties");
//		Properties props = new Properties();
//		try {
//			props.load(is);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//加载驱动程序
//		String driverclass = props.getProperty("driverclass");
//		System.out.println(driverclass);
//		String connecturl = props.getProperty("connecturl");
		//System.out.println(connecturl);
		try {
			//Class.forName(driverclass).newInstance();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//System.out.println(connecturl);
			conn = DriverManager.getConnection("jdbc:mysql://202.115.26.28:3306/mlibrary?user=root&password=719520&useUnicode=true&characterEncoding=UTF-8");//这个有问题啊，天哪，TMD,搞了一晚上，原来是mm.mysql-2.0.4-bin驱动程序和Mysql5.0不兼容阿
			//System.out.println(connecturl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println(connecturl);
			e.printStackTrace();
		}
		
		
		
		
	}

	/**构造函数
	 * 初始化数据库连接，加载驱动程序
	 */
	public UserDBBean() {
		super();
		// TODO Auto-generated constructor stub
		init();
	}
    public boolean hasUser(String uid)
    {
        int count = 0;
        try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("select count(*) from user where uid =  '"+uid+"'");
			
			if(rs.next()){
				count = rs.getInt(1);
				//rs.close();
			}
            stm.close();
			rs.close();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(count == 0){
             return false;
		}else return true;
    }
    public UserBean getUser(String uid)
    {
        UserBean user = new UserBean();
        user.setUID(uid);
        try {
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("select * from user where uid = '"+uid+"'");
			while(rs.next()){
               user.setPWD(rs.getString("pwd"));	 
			}
            stm.close();
			rs.close();
            
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return user;
    }
    public boolean updateUser(UserBean user){
        try {
			PreparedStatement stmt = conn.prepareStatement("update user set pwd = '"+user.getPWD()+"' where uid = '"+user.getUID()+"'");
			stmt.execute();
			stmt.close();
            return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
			
		}
    }
    public void releaseConn(){
        try{
            if(conn != null)
                this.conn.close(); 
        }catch(SQLException e ){
            
        }
        
    }
    public static void main(String args[]){
        UserBean user = new UserBean();
        user.setUID("23456");
        user.setPWD("111");
        UserDBBean userdb = new UserDBBean();
        boolean isSuccess = userdb.updateUser(user);
        System.out.println(isSuccess);
    }
}
