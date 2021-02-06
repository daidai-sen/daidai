package com.lanou.service;

import java.util.List;

import com.lanou.bean.Cart;

public interface ICartService {
	
    public int getByUserIdAndProductId(int userid,int productid) throws Exception;
    
	public List<Cart> getByDistinct(int userid,int pagenum,int pagecount) throws Exception;

	public List<Cart> getById(int userid) throws Exception;
	
	public List<Cart> getByPage(int pagenum,int pagecount) throws Exception;
	
	public int getCount() throws Exception;
	
    public void add(String username,String productname) throws Exception;
	
	public void update(String id,String username,String productname) throws Exception;
	
	public void delete(String id) throws Exception;
}
