package com.lanou.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lanou.bean.Admin;
import com.lanou.bean.User;
import com.lanou.util.JDBCUtil;

public class UserDaoImpl implements IUserDao{

	@Override
	public List<User> getByPage(int pagenum, int pagecount) throws Exception {
		List<User> userlist = new ArrayList<User>();
		String sql = "select * from t_user limit "+(pagenum-1)*pagecount+","+pagecount+" ";
	    ResultSet rs = JDBCUtil.queryUtil(sql, null);
	    while(rs.next()) {
	    	int id = rs.getInt("id");
	    	String name = rs.getString("name");
	    	String pwd = rs.getString("pwd");
	    	String phone = rs.getString("phone");
	    	String mail = rs.getString("mail");
	    	String address = rs.getString("address");
	    	User user = new User(id,name,pwd,phone,mail,address);
	    	userlist.add(user);
	    }
		return userlist;
		
	}

	@Override
	public int getCount() throws Exception {
		String sql = "select count(*) count from t_user";
		ResultSet rs = JDBCUtil.queryUtil(sql, null);
		while(rs.next()) {
			int count = rs.getInt("count");
			return count;
		}
		return 0;
	}

	@Override
	public void add(String name, String pwd, String phone, String mail,String address) throws Exception {
		String sql = "insert into t_user(name,pwd,phone,mail,address) values('"+name+"','"+pwd+"','"+phone+"','"+mail+"','"+address+"')";
		JDBCUtil.ZengShanGai(sql);		
	}

	@Override
	public void exit(String id, String name, String pwd, String phone, String mail,String address) throws Exception {
		String sql = "update t_user set name='"+name+"',pwd='"+pwd+"',phone='"+phone+"',mail='"+mail+"',address='"+address+"' where id="+id;
		JDBCUtil.ZengShanGai(sql);		
	}

	@Override
	public void delete(String id) throws Exception {
		String sql = "delete from t_user where id="+id;
		JDBCUtil.ZengShanGai(sql);		
	}

	@Override
	public List<User> getById(int id) throws Exception {
		List<User> userlist = new ArrayList<>();
		String sql = "select * from t_user where id = "+id;
		ResultSet rs = JDBCUtil.queryUtil(sql, null);	
		while(rs.next()) {
	    	String name = rs.getString("name");
	    	String pwd = rs.getString("pwd");
	    	String phone = rs.getString("phone");
	    	String mail = rs.getString("mail");
	    	String address = rs.getString("address");
	    	User user = new User(id,name,pwd,phone,mail,address);
	    	userlist.add(user);
		}
		return userlist;
	}

	@Override
	public User getByNameAndPwd(String name, String pwd) throws Exception {
		String sql = "select * from t_user where name=? and pwd=? ";
		Object[] os = {name,pwd};
		ResultSet rs = JDBCUtil.queryUtil(sql, os);
		while(rs.next()){
			int id = rs.getInt("id");
			User user = new User(id,name,pwd);
			return user;
		}
		return null;
	}

	
}
