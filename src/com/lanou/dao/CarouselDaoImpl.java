package com.lanou.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lanou.bean.Carousel;
import com.lanou.bean.User;
import com.lanou.util.JDBCUtil;

public class CarouselDaoImpl implements ICarouselDao{

	@Override
	public List<Carousel> getByPage(int pagenum, int pagecount) throws Exception {
		List<Carousel> carlist = new ArrayList<Carousel>();
		String sql = "select * from t_carousel limit "+(pagenum-1)*pagecount+","+pagecount+" ";
	    ResultSet rs = JDBCUtil.queryUtil(sql, null);
	    while(rs.next()) {
	    	int id = rs.getInt("id");
	    	String timg = rs.getString("timg");
	    	Carousel carousel = new Carousel(id,timg);
	    	carlist.add(carousel);
	    }
		return carlist;
		
	}
	@Override
	public int getCount() throws Exception {
		String sql = "select count(*) count from t_carousel";
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
			int count = rs.getInt("count");
			return count;
		}
		return 0;
	}
	@Override
	public void add(String timg) throws Exception {
		String sql = "insert into t_carousel(timg) values('"+timg+"')";
		JDBCUtil.ZengShanGai(sql);
	}
	@Override
	public void edit(String id, String timg) throws Exception {
		String sql = "update t_carousel set timg='"+timg+"' where id= "+id;
		JDBCUtil.ZengShanGai(sql);
	}
	@Override
	public void delete(String id) throws Exception {
		String sql = "delete from t_carousel where id="+id;
		JDBCUtil.ZengShanGai(sql);
	}

}
