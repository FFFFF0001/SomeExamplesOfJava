package cn.itcast.bookstore.servlet;

import java.sql.SQLException;

import javax.activation.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.itcast.bookstore.domain.Notice;
import cn.itcast.bookstore.utils.DataSourceUtils;

public class NoticeDao {

	public Notice getRecentNotice()throws SQLException {
		// TODO Auto-generated method stub
		
		DataSource dataSource=DataSourceUtils.getDataSource();
		QueryRunner queryRunner=new QueryRunner(dataSource);
		
		String sql="select * from notice order by n_time desc limit 0,1";
		return queryRunner.query(sql,new BeanHandler<Notice>(Notice.class));
		
	}

}
