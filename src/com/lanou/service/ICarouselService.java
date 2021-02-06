package com.lanou.service;

import java.util.List;

import com.lanou.bean.Carousel;

public interface ICarouselService {

	public List<Carousel> getByPage(int pagenum,int pagecount) throws Exception;
	
	public int getCount() throws Exception;
	
    public void add(String timg) throws Exception;
	
	public void edit(String id,String timg) throws Exception;
	
	public void delete(String id) throws Exception;
}
