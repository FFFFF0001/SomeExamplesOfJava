package service;

 

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;  
  
import bean.User;  
import dao.UserDao;  
  
/** 
 * @author Beauxie 
 * Service层 
 */  
  
@Service//这个属性对应的是业务层一般为Service层)，说明交给spring管理，而对应的包下的类名也会有一个"S"  
public class UserService {  
  
    @Autowired//同样是自动注入  
    private UserDao userDao;  
      
    public void addUser(User user){  
        //调用Dao层的addUser方法  
        userDao.addUser(user);  
    }  
}  