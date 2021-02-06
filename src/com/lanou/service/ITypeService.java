package com.lanou.service;

import java.util.List;

import com.lanou.bean.Type;

public interface ITypeService {

	public List<Type> getAll() throws Exception;
	
    public void add(String title, int parentid) throws Exception;
	
	public void edit(String id,String title) throws Exception;
	
	public void delete(String id) throws Exception;
}
