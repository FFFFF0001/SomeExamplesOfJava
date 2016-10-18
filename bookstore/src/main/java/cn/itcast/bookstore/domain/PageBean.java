package cn.itcast.bookstore.domain;

import java.awt.List;
import java.io.Serializable;

public class PageBean implements Serializable {
	private int currentPage;//当前页码
	private int totalCount;//总条数
	private int totalPage;//总页数	
	private int currentCount;//每页条数
	//private List<Products> ps;//每页显示的数据
	private String category;//类别
	private String searchfieldString;//模糊搜索的图书名
	
}
