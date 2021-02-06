package com.lanou.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lanou.bean.Admin;
import com.lanou.util.JDBCUtil;

public class AdminDaoImpl implements IAdminDao{

	@Override
	public Admin getByNameAndPwd(String adminname,String adminpwd) throws Exception {
		String sql = "select * from t_admin where adminname = ? and adminpwd = ?";
		Object[] os = {adminname,adminpwd};
		ResultSet rs = JDBCUtil.queryUtil(sql, os);
		while(rs.next()) {
			int id = rs.getInt("id");
			Admin admin = new Admin(id,adminname,adminpwd);
			return admin;
		}
		return null;
	}
	
	@Override
	public List<Admin> getByPage(int pagenum, int pagecount) throws Exception {
		List<Admin> adlist = new ArrayList<Admin>();
		String sql = "select * from t_admin limit "+(pagenum-1)*pagecount+","+pagecount+" ";
	    ResultSet rs = JDBCUtil.queryUtil(sql, null);
	    while(rs.next()) {
	    	int id =rs.getInt("id");
	    	String adminname = rs.getString("adminname");
	    	String adminpwd = rs.getString("adminpwd");
	    	Admin admin = new Admin(id,adminname,adminpwd);
	    	adlist.add(admin);
	    }
		return adlist;
	}

	@Override
	public int getCount() throws Exception {
		String sql = "select count(*) count from t_admin";
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
			int count = rs.getInt("count");
			return count;
		}
		return 0;
	}

	@Override
	public void add(String adminname,String adminpwd) throws Exception {
        String sql = "insert into t_admin(adminname,adminpwd) values('"+adminname+"','"+adminpwd+"')";
		JDBCUtil.ZengShanGai(sql);
	}

	@Override
	public void exit(String id,String adminname, String adminpwd) throws Exception {
		String sql = "update t_admin set adminname='"+adminname+"',adminpwd='"+adminpwd+"'where id="+id;
		JDBCUtil.ZengShanGai(sql);
	}

	@Override
	public void delete(String id) throws Exception {
		String sql = "delete from t_admin where id="+id;
		JDBCUtil.ZengShanGai(sql);
	}

}
