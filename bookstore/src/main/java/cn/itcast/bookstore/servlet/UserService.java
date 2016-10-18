package cn.itcast.bookstore.servlet;

import java.sql.SQLException;

import org.springframework.dao.support.DaoSupport;

import cn.itcast.bookstore.domain.User;
import cn.itcast.bookstore.exception.LoginException;
import cn.itcast.bookstore.exception.RegisterException;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.cn.hnust.dao.*;;
public class UserService {

	public User login(String username, String password) throws LoginException{
		// TODO Auto-generated method stub
		log.info("进入登录处理的service!!!");
		try {
			User user=dao.login(username,password);
			
			if(user!=null){
				if(user.getState()==1){
					return user;
				}else{
					log.error("用户未激活！");
				throw new LoginException("用户未激活！");
				}
				
			}else{
				log.error("请注意用户名或密码是否正确！");
				throw new LoginException("请注意用户名或密码是否正确！");
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("通过用户和密码查询用户出错！");
			throw new LoginException("通过用户和密码查询用户出错！");
		}
		return null;
	}

	public void regist(User user) {
		// TODO Auto-generated method stub
		try{
			dao.regist(user);
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}catch(RegisterException e){
			e.printStackTrace();
		}
	}

}
