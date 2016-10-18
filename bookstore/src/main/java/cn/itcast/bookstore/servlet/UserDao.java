package cn.itcast.bookstore.servlet;

import java.sql.Date;
import java.sql.SQLException;

import javax.activation.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.bookstore.domain.User;
import cn.itcast.bookstore.exception.RegisterException;
import cn.itcast.bookstore.utils.DataSourceUtils;

public class UserDao {
 public User login(String username,String password) throws SQLException{
	 log.info("进入登录dao程序");
	 DateSource ds=DataSource.getDataSource();
	 QueryRunner queryRunner=new QueryRunner();
	 
	 String sql ="select * from user where username=? and password=?";
	 log.info("sql语句:"+sql);
	 Object[] ob =new Object[]{username,password};
	 
	 return queryRunner.query(sql, new BeanHandler<User>(User.class),ob);
 }
 public int regist(User user )throws SQLException,RegisterException{
	 DataSource dataSource=DataSourceUtils.getDataSource();
	 QueryRunner queryRunner=new QueryRunner(dataSource);
	 
	 String sql="insert into user(username,password,gender,email,telephone,introduce,activecode)value(?,?,?,?,?,?,?)";
	 int i=queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),user.getIntroduce(),user.getActivecode());
	 if(i!=0)return 1;
	 else throw new RegisterException("注册用户不成功！！！");
 }
 }
