package com.cn.hnust.dao;

import java.sql.SQLException;

import javax.activation.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.bookstore.domain.Products;
import cn.itcast.bookstore.utils.DataSourceUtils;

public class ProductsDao {
public void test1(){
	Products p=null;
	DataSource dataSource=DataSourceUtils.getDataSource();
	QueryRunner queryRunner=new QueryRunner(dataSource);
	String sqlString="select * from products limit 0,1";
	try{
		p=queryRunner.query(sql, new BeanHandler<Products>(Products.class));
		
	}catch (SQLException e){
		e.printStackTrace();
	}
	if(p==null){
		System.out.println("查询对象为NULL");
	}else{
		System.out.println(p);
	}
	
}
}
