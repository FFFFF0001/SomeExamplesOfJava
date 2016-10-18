/*
 * BookDBBean.java
 *
 * Created on 2006��5��20��, ����6:57
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
 * @author ��è��ϣ
 */
public class BookDBBean {
    private Connection conn;
    
     /**
	 * ��ȡ���ݿ������ļ�db.properties�е����ݿ�������Ϣ
	 * ���Ҽ������ݿ���������
	 *
	 */
	private void init(){
		//��ȡ�����ļ��е���Ϣ
//		InputStream is = getClass().getResourceAsStream("db.properties");
//		Properties props = new Properties();
//		try {
//			props.load(is);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//������������
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
			conn = DriverManager.getConnection("jdbc:mysql://202.115.26.28:3306/mlibrary?user=root&password=719520&useUnicode=true&characterEncoding=UTF-8");//��������Ⱑ�����ģ�TMD,����һ���ϣ�ԭ����mm.mysql-2.0.4-bin���������Mysql5.0�����ݰ�
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
                   book.setState("�ڿ�ɽ�");
               }else if(state.equals("borrow")){
                   book.setState("���");
               }else{
                   book.setState("δ֪");
               }
               
               //��ѯ�����Ӧ������
               if(book.getState().equals("���")){
                   book.setReturndate(getReturnDate(book.getIndexid()));
               }else if(book.getState().equals("�ڿ�ɽ�")){
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
