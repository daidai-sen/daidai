package com.lanou.dao;

import java.util.List;

import com.lanou.bean.Type;

public interface ITypeDao {
    public List<Type> getById(int id) throws Exception;

	public List<Type> getByParentId(int parentid) throws Exception;
	
	public void add(String title, int parentid) throws Exception;
	
	public void edit(String id,String title) throws Exception;
	
	public void delete(String id) throws Exception;
}
