package com.lanou.dao;

import java.util.List;

import com.lanou.bean.Carousel;
import com.lanou.bean.User;

public interface ICarouselDao {
	
	public List<Carousel> getByPage(int pagenum,int pagecount) throws Exception;
	
	public int getCount() throws Exception;
	
    public void add(String timg) throws Exception;
	
	public void edit(String id,String timg) throws Exception;
	
	public void delete(String id) throws Exception;
}
