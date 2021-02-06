package com.lanou.service;

import java.util.List;

import com.lanou.bean.Admin;

public interface IAdminService {

	public Admin getByNameAndPwd(String adminname,String adminpwd) throws Exception;
	
    public List<Admin> getByPage(int pagenum,int pagecount) throws Exception;
	
	public int getCount() throws Exception;
	
	public void add(String adminname,String adminpwd) throws Exception;
	
	public void exit(String id,String adminname,String adminpwd) throws Exception;
	
	public void delete(String id) throws Exception;
}
