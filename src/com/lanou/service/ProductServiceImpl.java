package com.lanou.service;

import java.util.List;

import com.lanou.bean.Product;
import com.lanou.dao.IProductDao;
import com.lanou.dao.ProductDaoImpl;

public class ProductServiceImpl implements IProductService{
	private IProductDao ipDao = new ProductDaoImpl();
	@Override
	public List<Product> getByPage(int pagenum, int pagecount) throws Exception {
		return ipDao.getByPage(pagenum, pagecount);
	}

	@Override
	public int getCount() throws Exception {
		return ipDao.getCount();
	}
	@Override
	public void add(String pname, String pimg, double price, String ptitle, int pv, String typename) throws Exception {
		ipDao.add(pname, pimg, price, ptitle, pv, typename);
	}

	@Override
	public void edit(String id, String pname, String pimg, double price, String ptitle, int pv, String typename)
			throws Exception {
		ipDao.edit(id, pname, pimg, price, ptitle, pv, typename);
	}

	@Override
	public void delete(String id) throws Exception {
		ipDao.delete(id);
	}

	@Override
	public List<Product> getByTypeId(int typeid) throws Exception {
		return ipDao.getByTypeId(typeid);
	}

	@Override
	public List<Product> getByName(String pname) throws Exception {
		return ipDao.getByName(pname);
	}

}
