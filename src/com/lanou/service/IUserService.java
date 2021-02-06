package com.lanou.service;

import java.util.List;

import com.lanou.bean.User;

public interface IUserService {

    public List<User> getByPage(int pagenum,int pagecount) throws Exception;
    
    public User getByNameAndPwd(String name,String pwd) throws Exception;
	
	public int getCount() throws Exception;
	
	public void add(String name,String pwd,String phone,String mail,String address) throws Exception;
	
	public void exit(String id,String name,String pwd,String phone,String mail,String address) throws Exception;
	
	public void delete(String id) throws Exception;
}
