/*
 * BookDBBean.java
 *
 * Created on 2006年5月20日, 下午6:57
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
import java.util.Date;
/**
 *
 * @author 熊猫晓希
 */
public class BookDBBean {
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

    /** Creates a new instance of BookDBBean */
    public BookDBBean() {
        super();
		// TODO Auto-generated constructor stub
		init();
    }
    
    public List getBooks(String bookname){
        List ret = new ArrayList();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from book where bookname like '%"+bookname+"%'");
           
            while(rs.next()){
               BookBean book = new BookBean();
               String s = null;
//               try{
//                   s = new String(rs.getString("bookname").getBytes("GBK"),"UTF-8");
//               }catch(java.io.UnsupportedEncodingException e){
//                   System.out.println("dsfas");
//               }
               //System.out.println(s);
               book.setIndexid(rs.getString("indexid"));
               book.setBookname(rs.getString("bookname"));
               book.setAuthor(rs.getString("author"));
               book.setPublisher(rs.getString("publisher"));
               book.setPubdate(rs.getString("pubdate"));
               String state = rs.getString("state");
               if (state.equals("inlib")){
                   book.setState("在库可借");
               }else if(state.equals("borrow")){
                   book.setState("借出");
               }else{
                   book.setState("未知");
               }
               
               //查询本书的应还日期
               if(book.getState().equals("借出")){
                   book.setReturndate(getReturnDate(book.getIndexid()));
               }else if(book.getState().equals("在库可借")){
                   book.setReturndate("");
               }
                   
               ret.add(book);
            }
            stm.close();
            rs.close();
            
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        return ret;
    }
    public List getBooksforUser(String userid){
        List ret = new ArrayList();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from borrowsituation where uid = '"+userid+"'");
           
            while(rs.next()){
               BorrowBean borrow = new BorrowBean();
               

               borrow.setIndexid(rs.getString("indexid"));
               borrow.setBookname(getBookname(borrow.getIndexid()));
               borrow.setReturndate(rs.getString("returndate"));
               ret.add(borrow);
            }
            stm.close();
            rs.close();
            
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        return ret;
    }
    private String getBookname(String indexid){
        String bookname = "";
         try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select bookname from book where indexid = '"+indexid+"'");
            while(rs.next()){
                bookname = rs.getString("bookname");
            }
        }catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
        return bookname;
    }
    public boolean hasBook(String bookname){
        boolean flag = false;
        try {
			Statement stm = conn.createStatement();
			
            ResultSet rs = stm.executeQuery("select count(*) from book where bookname like '%"+bookname+"%'");

            int count = 0;
			if(rs.next()){
				count = rs.getInt(1);
				rs.close();
                stm.close();
			}
            
            if(count != 0)
                flag = true;
            else flag = false;
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return flag;
    }
    public boolean hasBookforUser(String userid){
        boolean flag = false;
        try {
            Statement stm = conn.createStatement();
			
            ResultSet rs = stm.executeQuery("select count(*) from borrowsituation where uid = '"+userid+"'");

            int count = 0;
            if(rs.next()){
		count = rs.getInt(1);
		rs.close();
                stm.close();
            }
            
            if(count != 0)
                flag = true;
            else flag = false;
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return flag;
    }
    
    private String getReturnDate(String indexid){
        String returnDate = "";
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select returndate from borrowsituation where indexid = '"+indexid+"'");
            while(rs.next()){
                returnDate = rs.getString("returndate");
            }
        }catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return returnDate;
    }
    
    public void releaseConn(){
        try{
            if(conn != null)
                this.conn.close(); 
        }catch(SQLException e ){
            
        }
    }
    
    
}
