package com.lanou.service;

import java.sql.ResultSet;
import java.util.List;

import com.lanou.bean.Carousel;
import com.lanou.dao.CarouselDaoImpl;
import com.lanou.dao.ICarouselDao;
import com.lanou.util.JDBCUtil;

public class CarouselServiceImpl implements ICarouselService{
    private ICarouselDao icDao = new CarouselDaoImpl();

    @Override
	public List<Carousel> getByPage(int pagenum, int pagecount) throws Exception {
		return icDao.getByPage(pagenum, pagecount);
	}

	@Override
	public int getCount() throws Exception {
		return icDao.getCount();
	}

	@Override
	public void add(String timg) throws Exception {
		icDao.add(timg);
	}

	@Override
	public void edit(String id, String timg) throws Exception {
        icDao.edit(id, timg);		
	}

	@Override
	public void delete(String id) throws Exception {
		icDao.delete(id);
	}

}
