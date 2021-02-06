package com.lanou.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lanou.bean.Type;
import com.lanou.util.JDBCUtil;

public class TypeDaoImpl implements ITypeDao{

	@Override
	public List<Type> getByParentId(int parentid) throws Exception {
		List<Type> typelist =new ArrayList<>();
		String sql = "select * from pro_type where parentid=? ";	
		Object[] os = {parentid};
		ResultSet rs = JDBCUtil.queryUtil(sql, os);
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("typename");
			Type type = new Type(id,title,parentid,null);
			typelist.add(type);
		}
		return typelist;
	}

	@Override
	public void add(String title, int parentid) throws Exception {
		String sql = "insert into pro_type(typename,parentid) values('"+title+"',"+parentid+")";
	    JDBCUtil.ZengShanGai(sql);
	}
	
	@Override
	public void edit(String id, String title) throws Exception {
		String sql = "update pro_type set typename='"+title+"' where id="+id;
		JDBCUtil.ZengShanGai(sql);
	}
	
	@Override
	public void delete(String id) throws Exception {
		String sql = "delete from pro_type where id="+id;
		JDBCUtil.ZengShanGai(sql);
	}

	@Override
	public List<Type> getById(int id) throws Exception {
		List<Type> typelist = new ArrayList<>();
		String sql = "select * from pro_type where id=?";
		Object[] os = {id};
		ResultSet rs = JDBCUtil.queryUtil(sql, os);
		while(rs.next()) {
			String title = rs.getString("typename");
			int parentid = rs.getInt("parentid");
			Type type = new Type(id,title,parentid,null);
			typelist.add(type);
		}
		return typelist;
	}

}
