package cn.itcast.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.hnust.service.impl.UserServiceImpl;
import com.sun.istack.internal.logging.Logger;

import cn.itcast.bookstore.domain.User;
import cn.itcast.bookstore.exception.LoginException;

public class LoginServlet extends HttpServlet{
	Logger log=Logger.getLogger(LoginServlet.class);
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		doPost(request, response);
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		
		response.setContentType("text/html);charset=utf-8");
		
		PrintWriter out =response.getWriter();
		
		log.info("进入登录的servlet!!!");
		
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		log.info("传递的用户名为："+username);
		
		UserService service =new UserService ();
		String path=request.getContentPath();
		try {
			User user=service.login(username,password);
			request.getSession().setAttribute("user", user);
			log.info("当前用户的角色为："+user.getRole());
		if (user.getRole().equals("超级用户")) {
			response.sendRedirect(path+"/admin/login/home.jsp");
			return;
			// TODO: handle exception
		}else{
			response.sendRedirect(path+"/client/index.jsp?user="+URLEncoder.encode(username,"UTF-8"));
			return;
			
		}catch(LoginException e){
			e.printStackTrace();
			log.error("登陆出错，错误信息为:"+e.getMessage());
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
		}
	}	
}
 