package com.lanou.service;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.lanou.bean.Cart;
import com.lanou.bean.Product;
import com.lanou.bean.User;
import com.lanou.dao.CartDaoImpl;
import com.lanou.dao.ICartDao;
import com.lanou.dao.IProductDao;
import com.lanou.dao.ProductDaoImpl;

public class CartServiceImpl implements ICartService{
    private ICartDao icDao = new CartDaoImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Cart> getByPage(int pagenum, int pagecount) throws Exception {
		return icDao.getByPage(pagenum, pagecount);
	}
		
	@Override
	public int getCount() throws Exception {
		return icDao.getCount();
	}

	@Override
	public void add(String username, String productname) throws Exception {
		icDao.add(username, productname);
	}

	@Override
	public void update(String id, String username, String productname) throws Exception {
		icDao.update(id, username, productname);
	}

	@Override
	public void delete(String id) throws Exception {
		icDao.delete(id);
	}

	@Override
	public List<Cart> getById(int userid) throws Exception {
		return icDao.getById(userid);
	}

	@Override
	public int getByUserIdAndProductId(int userid, int productid) throws Exception {
		return icDao.getCount();
	}

	@Override
	public List<Cart> getByDistinct(int userid, int pagenum, int pagecount) throws Exception {
		return icDao.getByDistinct(userid, pagenum, pagecount);
	}
    
}
