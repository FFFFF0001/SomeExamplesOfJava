package com.cn.hnust.service;

import java.sql.SQLException;

import javax.activation.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.cn.hnust.dao.ProductsDao;

import cn.itcast.bookstore.domain.PageBean;
import cn.itcast.bookstore.utils.DataSourceUtils;

public class ProductsService {
	 private static ProductsDao dao=new ProductsDao();
	 	public static void main(String[] args){
	 		System.out.println(dao.testdao());
	 		
	 	}
	 public PageBean showpage(String category,int currentPage,int currentCount){
		 PageBean bean=new PageBean();
		 bean.setCurrentCount(currentCount);
		 bean.setCategory(category);
		 bean.setCurrentPage(currentpage);
		 
		 
	 }
}
