package com.lanou.dao;

import java.util.List;

import com.lanou.bean.Carousel;
import com.lanou.bean.Product;
import com.lanou.bean.User;

public interface IProductDao {
	public List<Product> getById(int id) throws Exception;
	
	public List<Product> getByName(String pname) throws Exception;
	
	public List<Product> getByTypeId(int typeid) throws Exception;
	
    public List<Product> getByPage(int pagenum,int pagecount) throws Exception;
	
	public int getCount() throws Exception;
	
    public void add(String pname, String pimg, double price, String ptitle, int pv, String typename) throws Exception;
	
	public void edit(String id,String pname, String pimg, double price, String ptitle, int pv, String typename) throws Exception;
	
	public void delete(String id) throws Exception;
}
