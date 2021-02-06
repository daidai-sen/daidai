package com.lanou.service;

import java.util.List;

import com.lanou.bean.Product;

public interface IProductService {
	    public List<Product> getByName(String pname) throws Exception;
	    
	    public List<Product> getByPage(int pagenum,int pagecount) throws Exception;
		
	    public List<Product> getByTypeId(int typeid) throws Exception;
	    
		public int getCount() throws Exception;
		
	    public void add(String pname, String pimg, double price, String ptitle, int pv,String typename) throws Exception;
		
		public void edit(String id,String pname, String pimg, double price, String ptitle, int pv, String typename) throws Exception;
		
		public void delete(String id) throws Exception;
}
