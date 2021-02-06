package com.lanou.service;

import java.util.List;

import com.lanou.bean.User;
import com.lanou.dao.IUserDao;
import com.lanou.dao.UserDaoImpl;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class UserServiceImpl implements IUserService{
    
	private IUserDao iuDao = new UserDaoImpl();

	@Override
	public List<User> getByPage(int pagenum, int pagecount) throws Exception {
		return iuDao.getByPage(pagenum, pagecount);
	}

	@Override
	public int getCount() throws Exception {
		return iuDao.getCount();
	}

	@Override
	public void add(String name, String pwd, String phone, String mail,String address) throws Exception {
        iuDao.add(name, pwd, phone, mail,address);		
	}

	@Override
	public void exit(String id, String name, String pwd, String phone, String mail,String address) throws Exception {
		iuDao.exit(id, name, pwd, phone, mail,address);
	}

	@Override
	public void delete(String id) throws Exception {
		iuDao.delete(id);
	}

	@Override
	public User getByNameAndPwd(String name, String pwd) throws Exception {
		return iuDao.getByNameAndPwd(name, pwd);
	}

}
