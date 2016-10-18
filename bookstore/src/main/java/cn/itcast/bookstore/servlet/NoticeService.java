package cn.itcast.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.domain.Notice;

import java.sql.SQLException;

public class NoticeService{
	private NoticeDao dao=new NoticeDao();
	private Notice getRecentNotice(){
		try{
			return dao.getRecentNotice();
		}catch(SQLException e){ 
			e.printStackTrace();
			throw new RuntimeException("获取公共信息出错");
		}
		
	}
}

