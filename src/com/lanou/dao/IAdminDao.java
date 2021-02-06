package com.lanou.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanou.bean.Admin;

public interface IAdminDao {

	public Admin getByNameAndPwd(@Param("name")String adminname,@Param("pwd")String adminpwd) throws Exception;
	
	public List<Admin> getByPage(int pagenum,int pagecount) throws Exception;
	
	public int getCount() throws Exception;
	
	public void add(String adminname,String adminpwd) throws Exception;
	
	public void exit(String id,String adminname,String adminpwd) throws Exception;
	
	public void delete(String id) throws Exception;
	
}
