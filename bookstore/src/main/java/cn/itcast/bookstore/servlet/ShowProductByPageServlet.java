package cn.itcast.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.hnust.service.ProductsService;

import cn.itcast.bookstore.domain.PageBean;

/**
 * Servlet implementation class ShowProductByPageServlet
 */
@WebServlet("/ShowProductByPageServlet")
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductByPageServlet() {
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
		PrintWriter out=response.getWriter();
		//获取页面传递过来的分类，如果页面又传递值，就使用页面传递值，如果没有就使用默认值
		String category="全部书籍";
		String category1=request.getParameter("category");
		if(category1!=null){
			category=category1;
			
		}
		//当前页
		int currentPage=1;
		String currentPage1=request.getParameter("currentPage");
		if(currentPage1!=null){
			currentPage=Integer.parseInt(currentPage1);
			
			
		}
		//每页显示条数
		int currentCount=4;
		String currentCount1=request.getParameter("currentCount");
		if(currentCount1!=null){
			currentCount=Integer.parseInt(currentCount1);
			
		}
		//创建service
		ProductsService productsService=new ProductsService();
		PageBean bean=productsService.showpage(category,currentPage,currentCount);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		return;
	}

}
