package cn.itcast.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.mchange.v2.codegen.bean.BeangenUtils;

import cn.itcast.bookstore.domain.User;
import cn.itcast.bookstore.utils.ActiveCode;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		
		User user= new User();
		try{
			String checkstr =request.getParameter("checkstr");
			
			String checkcode_session=(String)request.getSession().getAttribute("checkcode_session");
			
			if(!checkcode_session.equals(checkstr)){
				return;
			}
			BeanUtils.populate(user, request.getParameterMap());
			
			user.setActivecode(ActiveCode.generateActicecode());
			
			UserService service=new UserService();
			
			service.regist(user);
			
			response.sendRedirect(request.getContextPath()+"/client/registeruccess.jsp");
			
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}
		return;
		
	}

}
